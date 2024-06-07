package dev.ykvlv.melo.application.parser;

import dev.ykvlv.melo.application.MeloParserProperties;
import dev.ykvlv.melo.domain.entity.User;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class VkMusicParserImpl implements ParserStrategy {

    private final FilterService filterService;
    private final MeloParserProperties.MusicParserProperties parserProperties;
    private final WebClient webClient;

    public VkMusicParserImpl(FilterService filterService,
                             MeloParserProperties meloParserProperties) {
        this.filterService = filterService;
        this.parserProperties = meloParserProperties.getVkMusic();
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

    public void parseUser(@NonNull User user) {

    }
}
