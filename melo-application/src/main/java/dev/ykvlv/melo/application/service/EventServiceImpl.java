package dev.ykvlv.melo.application.service;

import dev.ykvlv.melo.application.exception.BEWrapper;
import dev.ykvlv.melo.application.exception.BusinessException;
import dev.ykvlv.melo.application.service.search.SearchEventService;
import dev.ykvlv.melo.commons.request.search.SearchEventsRequest;
import dev.ykvlv.melo.commons.response.EventResponse;
import dev.ykvlv.melo.commons.response.PagingResult;
import dev.ykvlv.melo.commons.response.SearchEventsResponse;
import dev.ykvlv.melo.domain.entity.City;
import dev.ykvlv.melo.domain.entity.Event;
import dev.ykvlv.melo.domain.entity.User;
import dev.ykvlv.melo.domain.mapper.EventMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dev.ykvlv.melo.domain.repository.EventRepository;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final SearchEventService searchEventService;

    @NonNull
    @Override
    @Transactional(readOnly = true)
    public EventResponse read(@NonNull Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new BEWrapper(BusinessException.EVENT_NOT_FOUND, id));

        return eventMapper.mapFull(event);
    }

    @NonNull
    @Override
    public SearchEventsResponse search(@NonNull SearchEventsRequest searchEventsRequest, User user) {
        var slice = searchEventService.searchEvents(searchEventsRequest, user);

        var events = slice.getContent().stream()
                .map(eventMapper::map)
                .toList();

        return SearchEventsResponse.builder()
                .pagingResult(PagingResult.builder()
                        .pageSize(slice.getSize())
                        .pageNumber(slice.getNumber())
                        .morePagesAvailable(slice.hasNext())
                        .build())
                .events(events)
                .build();
    }

}
