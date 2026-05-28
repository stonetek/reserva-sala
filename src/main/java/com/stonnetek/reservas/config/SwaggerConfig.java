package com.stonnetek.reservas.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("API Reserva de Salas")
                        .description("API para gerenciamento de reservas de salas e auditórios.")
                        .version("1.0.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub do Projeto"));
    }
}
