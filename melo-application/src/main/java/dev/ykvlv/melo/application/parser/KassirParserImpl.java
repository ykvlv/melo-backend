package dev.ykvlv.melo.application.parser;

import dev.ykvlv.melo.application.MeloParserProperties;
import dev.ykvlv.melo.domain.mapper.ParserMapper;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class KassirParserImpl implements ParserStrategy {

    private final ParserMapper parserMapper;
    private final MeloParserProperties.EventParserProperties parserProperties;
    private final WebClient webClient;
    private final FilterService filterService;

    public KassirParserImpl(ParserMapper parserMapper,
                            MeloParserProperties meloParserProperties,
                            FilterService filterService) {
        this.parserMapper = parserMapper;
        this.parserProperties = meloParserProperties.getKassir();
        this.filterService = filterService;
        this.webClient = WebClient.builder()
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(parserProperties.getHeaders()))
                .build();
    }

    @Override
    public void parse() {

    }

    @Override
    public void handleError(@NonNull Exception e) {

    }
}
