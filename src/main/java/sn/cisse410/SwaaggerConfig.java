package sn.cisse410;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("API de gestion des produits").version("1.0")
                .description("Ceci est un simple Spring RESTful pour la gestion produits d'un stock"));
    }
}
