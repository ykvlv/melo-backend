package dev.ykvlv.melo.application;

import lombok.Data;
import lombok.NonNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "melo.application")
public class MeloProperties {

    @NonNull
    private String jwtSecret;

    @NonNull
    private String yaMusicClientId;

    @NonNull
    private String yaMusicClientSecret;

}
