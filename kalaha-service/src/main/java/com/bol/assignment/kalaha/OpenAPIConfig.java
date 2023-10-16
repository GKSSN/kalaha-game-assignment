package com.bol.assignment.kalaha;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The OpenAPIConfig class is a Java configuration class that creates and configures an OpenAPI object for a Khala Game
 * Application.
 */
@Configuration
public class OpenAPIConfig {

    /**
     * The function creates and returns an OpenAPI object with information about a Khala game application.
     *
     * @return The method is returning an instance of the OpenAPI class.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("Khala Service")
                .description("Khala Game Application")
                .version("1.0"));
    }
}
