package com.learning.urlshortener.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configures the Swagger UI page (available at /swagger-ui.html once the app is running).
// Just gives our API a title in the docs -- nothing fancy needed since there's no auth.
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("URL Shortener API").version("1.0"));
    }
}
