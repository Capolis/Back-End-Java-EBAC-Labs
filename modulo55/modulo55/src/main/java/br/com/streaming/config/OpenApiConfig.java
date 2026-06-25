package br.com.streaming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig{

    // Configura os metadados da página do Swagger UI
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Streaming Platform API")
                        .version("1.0")
                        .description("API para gerenciamento de usuários, filmes e histórico de visualizações."));
    }
}