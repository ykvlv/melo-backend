package dev.ykvlv.melo.parser.service;

import dev.ykvlv.melo.commons.parser.EventData;
import dev.ykvlv.melo.parser.MeloParserProperties;

import dev.ykvlv.melo.commons.parser.ParserType;
import lombok.NonNull;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public abstract class AbstractParser<T extends ParserType> implements ParserStrategy {

    protected final MeloParserProperties.ParserProperties parserProperties;
    protected final EventFilterService eventFilterService;
    private final WebClient webClient;

    protected AbstractParser(@NonNull MeloParserProperties.ParserProperties parserProperties,
                             EventFilterService eventFilterService) {
        this.parserProperties = parserProperties;
        this.eventFilterService = eventFilterService;
        this.webClient = WebClient.builder()
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(parserProperties.getHeaders()))
                .build();
    }

    /**
     * Парсит данные с параметрами.
     *
     * @param url           URL запроса
     * @param params        параметры запроса
     * @param responseType  тип данных
     * @return результат запроса
     * @param <V> тип данных
     */
    protected final <V> V parseWithParams(@NonNull String url,
                                          @NonNull MultiValueMap<String, String> params,
                                          @NonNull Class<V> responseType) {
        return webClient.get()
                .uri(url, uriBuilder -> uriBuilder.queryParams(params).build())
                .retrieve()
                .bodyToMono(responseType)
                .block();
    }

    protected abstract List<EventData> map(@NonNull T entity);

    @Override
    public void handleError(@NonNull Exception e) {
        e.printStackTrace();
    }

}
