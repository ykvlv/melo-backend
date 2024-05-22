package dev.ykvlv.melo.application.parser;

import dev.ykvlv.melo.commons.parser.EventData;
import dev.ykvlv.melo.domain.entity.Artist;
import dev.ykvlv.melo.domain.entity.City;
import dev.ykvlv.melo.domain.entity.Event;
import dev.ykvlv.melo.domain.entity.Stage;
import dev.ykvlv.melo.domain.entity.filter.ArtistFilter;
import dev.ykvlv.melo.domain.entity.filter.CityFilter;
import dev.ykvlv.melo.domain.entity.filter.StageFilter;
import dev.ykvlv.melo.domain.entity.filter.StageFilterId;
import dev.ykvlv.melo.domain.repository.EventRepository;
import dev.ykvlv.melo.domain.repository.filter.ArtistFilterRepository;
import dev.ykvlv.melo.domain.repository.filter.CityFilterRepository;
import dev.ykvlv.melo.domain.repository.filter.StageFilterRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventFilterService {
    private final CityFilterRepository cityFilterRepository;
    private final StageFilterRepository stageFilterRepository;
    private final ArtistFilterRepository artistFilterRepository;
    private final EventRepository eventRepository;

    @Transactional
    public void saveData(@NonNull List<EventData> data) {
        data.forEach((e) -> {
            var optionalCity = getCity(e.getCityName());
            if (optionalCity.isEmpty()) {
                return;
            }

            var optionalStage = getStage(e.getStageName(), optionalCity.get());
            if (optionalStage.isEmpty()) {
                return;
            }

            var optionalArtist = getArtist(e.getArtistName());
            if (optionalArtist.isEmpty()) {
                return;
            }

            saveEvent(e, optionalArtist.get(), optionalStage.get());
        });
    }

    @NonNull
    private Optional<City> getCity(@NonNull String cityName) {
        // Ищет город по названию
        var optionalCityFilter = cityFilterRepository.findById(cityName);
        if (optionalCityFilter.isEmpty()) {

            // Если не нашел – добавит на рассмотрение
            var cityFilter = CityFilter.builder()
                    .name(cityName)
                    .banned(false)
                    .build();
            cityFilterRepository.save(cityFilter);

            log.info("Город {} добавлен на рассмотрение", cityName);
            return Optional.empty();
        }

        var cityFilter = optionalCityFilter.get();

        if (cityFilter.getBanned()) {
            return Optional.empty();
        }

        // Если нашел и город не в черном списке – вернет город
        return Optional.ofNullable(cityFilter.getCity());
    }

    @NonNull
    private Optional<Artist> getArtist(@NonNull String artistName) {
        // Ищет артиста по названию
        var optionalArtistFilter = artistFilterRepository.findById(artistName);
        if (optionalArtistFilter.isEmpty()) {

            // Если не нашел – добавит на рассмотрение
            var artistFilter = ArtistFilter.builder()
                    .name(artistName)
                    .banned(false)
                    .build();
            artistFilterRepository.save(artistFilter);

            log.info("Артист {} добавлен на рассмотрение", artistName);
            return Optional.empty();
        }

        var artistFilter = optionalArtistFilter.get();

        if (artistFilter.getBanned()) {
            return Optional.empty();
        }

        // Если нашел и артист не в черном списке – вернет артиста
        return Optional.ofNullable(artistFilter.getArtist());
    }

    @NonNull
    private Optional<Stage> getStage(@NonNull String stageName, @NonNull City city) {
        // Ищет площадку по названию
        var stageFilterId = StageFilterId.builder()
                .name(stageName)
                .city(city)
                .build();
        var optionalStageFilter = stageFilterRepository.findById(stageFilterId);
        if (optionalStageFilter.isEmpty()) {

            // Если не нашел – добавит на рассмотрение
            var stageFilter = StageFilter.builder()
                    .name(stageName)
                    .city(city)
                    .banned(false)
                    .build();
            stageFilterRepository.save(stageFilter);

            log.info("Площадка {} добавлена на рассмотрение", stageName);
            return Optional.empty();
        }

        var stageFilter = optionalStageFilter.get();

        if (stageFilter.getBanned()) {
            return Optional.empty();
        }

        // Если нашел и площадка не в черном списке – вернет площадку
        return Optional.ofNullable(stageFilter.getStage());
    }

    private void saveEvent(@NonNull EventData eventData, @NonNull Artist artist, @NonNull Stage stage) {
        var date = eventData.getDate();

        var optionalEvent = eventRepository.findByArtistAndStageAndDate(artist, stage, date);

        // TODO перенести на мапер
        if (optionalEvent.isEmpty()) {
            eventRepository.save(Event.builder()
                    .artist(artist)
                    .stage(stage)
                    .date(date)
                    .createdAt(LocalDateTime.now())
                    .photoUrl(eventData.getPhotoUrl())
                    .kassirUrl(null) // TODO добавить определение URL
                    .afishaUrl(eventData.getUrl()) // TODO добавить URL
                    .build());

            log.info("Событие {} сохранено", eventData);
        } else {
            // TODO параметры то всякие могут меняться
            var event = optionalEvent.get();
            event.setPhotoUrl(eventData.getPhotoUrl());
            event.setAfishaUrl(eventData.getUrl());
            eventRepository.save(event);
        }
    }
}
