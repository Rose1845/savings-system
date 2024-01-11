package com.rose.savings.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import org.springframework.context.annotation.Bean;

@OpenAPI31
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new io.swagger.v3.oas.models.info
                        .Info()
                        .title("SAVINGS MANAGEMENT SYSTEM")
                        .description("RestFul APIs  ")
                        .version("1.0.0")
        );
    }
}
