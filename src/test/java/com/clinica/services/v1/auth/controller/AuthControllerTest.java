package com.clinica.services.v1.auth.controller;

import com.clinica.services.v1.auth.dto.AuthRequest;
import com.clinica.services.v1.auth.dto.AuthResponse;
import com.clinica.services.v1.auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

class AuthControllerTest {

    private AuthService authService;
    private WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        authService = Mockito.mock(AuthService.class);
        AuthController authController = new AuthController(authService);
        webTestClient = WebTestClient.bindToController(authController).build();
    }

    @Test
    void login_Success() {
        AuthRequest request = new AuthRequest();
        request.setUsername("user");
        request.setPassword("pass");

        Mockito.when(authService.authenticate(Mockito.any(AuthRequest.class)))
                .thenReturn(Mono.just(new AuthResponse("token123")));

        webTestClient.post()
                .uri("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.token").isEqualTo("token123");
    }

    @Test
    void login_Unauthorized() {
        AuthRequest request = new AuthRequest();
        request.setUsername("user");
        request.setPassword("wrongpass");

        Mockito.when(authService.authenticate(Mockito.any(AuthRequest.class)))
                .thenReturn(Mono.empty());

        webTestClient.post()
                .uri("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isUnauthorized();
    }
}
