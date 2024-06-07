package dev.ykvlv.melo.application;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Data
@Component
@ConfigurationProperties(value = "melo.parser")
public class MeloParserProperties {

    private EventParserProperties afisha;
    private EventParserProperties kassir;
    private MusicParserProperties yaMusic;
    private MusicParserProperties vkMusic;

    @Data
    public static class EventParserProperties {

        @NonNull
        private String eventsUrl;

        @NonNull
        private String citiesUrl;

        @NonNull
        private MultiValueMap<String, String> headers;

    }

    @Data
    public static class MusicParserProperties {

        @NonNull
        private String libraryUrl;

        @NonNull
        private String tracksUrl;

        @NonNull
        private MultiValueMap<String, String> headers;

    }

}
