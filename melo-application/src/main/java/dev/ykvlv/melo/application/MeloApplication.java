package dev.ykvlv.melo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MeloApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeloApplication.class, args);
	}

}
