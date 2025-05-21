package com.clinica.services.v1.config;

import com.clinica.services.v1.auth.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                //Desactiva protección CSRF para REST API
                .csrf(ServerHttpSecurity.CsrfSpec::disable)

                //Desactiva login y HTTP básico (usamos JWT)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)

                //Configura reglas de autorización
                .authorizeExchange(exchanges -> exchanges
                        // Rutas públicas (ej. login o registro)
                        .pathMatchers("/auth/**").permitAll()

                        //Rutas protegidas (requieren JWT)
                        .pathMatchers("/api/v1/**").authenticated()
                        //.pathMatchers("/api/v1/medicos/**").authenticated()

                        //Todo lo demás se deniega
                        .anyExchange().denyAll()
                )

                //Filtro JWT agregado al flujo de seguridad
                .addFilterAt(jwtFilter, SecurityWebFiltersOrder.AUTHENTICATION)

                .build();
    }
}
