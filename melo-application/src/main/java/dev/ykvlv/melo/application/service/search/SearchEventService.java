package dev.ykvlv.melo.application.service.search;

import dev.ykvlv.melo.application.exception.BEWrapper;
import dev.ykvlv.melo.application.exception.BusinessException;
import dev.ykvlv.melo.domain.entity.*;
import jakarta.persistence.criteria.*;
import dev.ykvlv.melo.commons.request.search.SearchEventsRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.support.PageableUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SearchEventService {

    @PersistenceContext
    private final EntityManager entityManager;

    public Slice<Event> searchEvents(SearchEventsRequest searchEventsRequest, User user) {
        CriteriaQuery<Event> cq = createCriteriaQuery(searchEventsRequest, user);

        var pagingOptions = searchEventsRequest.getPagingOptionsRequest();
        var pageRequest = PageRequest.of(pagingOptions.getPageNumber(), pagingOptions.getPageSize());

        TypedQuery<Event> query = entityManager.createQuery(cq)
                .setFirstResult(PageableUtils.getOffsetAsInteger(pageRequest))
                .setMaxResults(pageRequest.getPageSize() + 1); // на 1 больше, чтобы узнать есть ли следующая страница

        List<Event> events = query.getResultList();
        boolean hasNext = events.size() > pageRequest.getPageSize();

        return new SliceImpl<>(hasNext ? events.subList(0, pageRequest.getPageSize()) : events, pageRequest, hasNext);
    }

    private CriteriaQuery<Event> createCriteriaQuery(SearchEventsRequest request, User user) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Event> cq = builder.createQuery(Event.class);
		cq.distinct(true);

        var predicates = new ArrayList<Predicate>();
        var root = cq.from(Event.class);
        var joinArtist = root.join(Event_.artist, JoinType.LEFT);
        var joinStage = root.join(Event_.stage, JoinType.LEFT);
        var joinCity = joinStage.join(Stage_.city, JoinType.LEFT);

        var artistName = request.getArtistName();
        if (artistName != null) {
            artistName = artistName.trim().toLowerCase();
            var predicateForArtist = builder.like(
                    builder.lower(joinArtist.get(Artist_.name)),
                    "%" + artistName + "%");
            predicates.add(predicateForArtist);
        }

        var dateFrom = request.getDateFrom();
        if (dateFrom != null) {
            var predicateForDateFrom = builder.greaterThanOrEqualTo(root.get(Event_.date),
                    dateFrom);
            predicates.add(predicateForDateFrom);
        }

        var dateTo = request.getDateTo();
        if (dateTo != null) {
            var predicateForDateTo = builder.lessThanOrEqualTo(root.get(Event_.date),
                    dateTo);
            predicates.add(predicateForDateTo);
        }

        var anyCity = request.getAnyCity();
        if (anyCity == null || !anyCity) {
            predicates.add(builder.equal(joinCity.get(City_.id), user.getCity().getId()));
        }

        var onlyFavoriteArtists = request.getOnlyFavoriteArtists();
        if (onlyFavoriteArtists != null && onlyFavoriteArtists) {
            Long userId = user.getId();

            Subquery<Long> subquery = cq.subquery(Long.class);
            Root<UserFavoriteArtists> favoriteRoot = subquery.from(UserFavoriteArtists.class);
            subquery.select(favoriteRoot.get(UserFavoriteArtists_.artist).get(Artist_.id));

            Predicate userMatch = builder.equal(favoriteRoot.get(UserFavoriteArtists_.user).get(User_.id), userId);
            Predicate notBanned = builder.isFalse(favoriteRoot.get(UserFavoriteArtists_.banned));

            subquery.where(builder.and(userMatch, notBanned));

            predicates.add(builder.in(joinArtist.get(Artist_.id)).value(subquery));
        }

        cq.where(predicates.toArray(Predicate[]::new));

        // Параметры сортировки
        List<Order> orders = new ArrayList<>();
        var sortingOptions = request.getPagingOptionsRequest().getSortingOptionsRequests();

        for (int i = 0; i < sortingOptions.size(); i++) {
            var sortingOption = sortingOptions.get(i);
            boolean isDescending = Objects.requireNonNullElse(sortingOption.getDescending(), false);
            String attributeName = sortingOption.getAttributeName().trim();

            try {
                var path = root.get(attributeName);

                orders.add(isDescending ? builder.desc(path) : builder.asc(path));
            } catch (IllegalArgumentException e) {
                throw new BEWrapper(BusinessException.INVALID_SORTING_ATTRIBUTE,
                        String.format("pagingOptions.sortingOptions[%s].attributeName", i),
                        attributeName);
            }
        }
        cq.orderBy(orders);

        return cq;
    }
}
