package com.clinica.services.v1.config;

import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApplicationProperties {

    @Value("${clinica.api-server-url}")
    private String apiServerUrl;

    @Bean
    public OpenApiCustomizer dynamicServerOpenApiCustomizer() {
        return openApi -> {
            Server server = new Server();
            server.setUrl(apiServerUrl);
            server.setDescription("Servidor según entorno");
            openApi.setServers(List.of(server));
        };
    }

}
