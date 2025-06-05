package com.Grupo10.EcoMarketSpa.Confing;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfing {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API EcoMarket SPA")
                        .version("1.0")
                        .description("Microservicios API REST de EcoMarket SPA")
                );
    }
}
