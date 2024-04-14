package dev.ykvlv.melo.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ConfigurationPropertiesScan
@EntityScan("dev.ykvlv.melo.domain.entity")
@EnableJpaRepositories("dev.ykvlv.melo.domain.repository")
@SpringBootApplication(scanBasePackages = {"dev.ykvlv.melo"})
public class MeloApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeloApplication.class, args);
	}

}
