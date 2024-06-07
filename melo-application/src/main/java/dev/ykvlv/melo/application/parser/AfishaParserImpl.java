package dev.ykvlv.melo.application.parser;

import dev.ykvlv.melo.application.MeloParserProperties;
import dev.ykvlv.melo.commons.parser.AfishaCities;
import dev.ykvlv.melo.commons.parser.AfishaEvents;
import dev.ykvlv.melo.commons.parser.EventData;
import dev.ykvlv.melo.domain.mapper.ParserMapper;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
public class AfishaParserImpl implements ParserStrategy {

    private final static int LIMIT = 20;

    private final ParserMapper parserMapper;
    private final MeloParserProperties.EventParserProperties parserProperties;
    private final WebClient webClient;
    private final FilterService filterService;

    public AfishaParserImpl(ParserMapper parserMapper,
                            MeloParserProperties meloParserProperties,
                            FilterService filterService) {
        this.parserMapper = parserMapper;
        this.parserProperties = meloParserProperties.getAfisha();
        this.filterService = filterService;
        this.webClient = WebClient.builder()
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(parserProperties.getHeaders()))
                .build();
    }

    @Override
    public void parse() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("city", "saint-petersburg"); // без этого параметра запрос не проходит

        // Парсим данные городов
        AfishaCities cities = webClient.get()
                .uri(parserProperties.getCitiesUrl(), uriBuilder -> uriBuilder.queryParams(params).build())
                .retrieve()
                .bodyToMono(AfishaCities.class)
                .block();

        // Парсим события каждого города
        for (AfishaCities.AfishaCity city : cities.getData()) {
            int offset = 0;

            // Устанавливаем параметры в запрос
            params.set("city", city.getId());
            params.set("hasMixed", String.valueOf(0));
            params.set("limit", String.valueOf(LIMIT));
            params.set("offset", String.valueOf(offset));

            AfishaEvents eventsData;
            do {
                // Отправляем запросы пачками по 20
                eventsData = webClient.get()
                        .uri(parserProperties.getEventsUrl(), uriBuilder -> uriBuilder.queryParams(params).build())
                        .retrieve()
                        .bodyToMono(AfishaEvents.class)
                        .block();

                // Преобразуем полученные данные в понятные приложению
                List<EventData> data = eventsData.getData().stream().map(parserMapper::map).toList();

                // Сохраняем данные через фильтрационный сервис
                filterService.saveData(data);

                params.set("offset", String.valueOf(offset += LIMIT));
            } while (eventsData.hasNext());
        }
    }

    @Override
    public void handleError(@NonNull Exception e) {
        e.printStackTrace();
    }

}
