package dev.ykvlv.melo.application;

import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Data
@Component
@ConfigurationProperties(value = "melo.parser")
public class MeloParserProperties {

    private ParserProperties afisha;

    @Data
    public static class ParserProperties {

        @NonNull
        private String eventsUrl;

        @NonNull
        private String citiesUrl;

        @NonNull
        private MultiValueMap<String, String> headers;
    }

}
