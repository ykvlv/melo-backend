package dev.ykvlv.melo.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "melo")
public class MeloProperties {

}
