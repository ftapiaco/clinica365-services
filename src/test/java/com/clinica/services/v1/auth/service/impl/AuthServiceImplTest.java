package com.clinica.services.v1.auth.service.impl;

import com.clinica.services.v1.auth.dto.AuthRequest;
import com.clinica.services.v1.auth.dto.AuthResponse;
import com.clinica.services.v1.auth.jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

public class AuthServiceImplTest {

    private final JwtUtil jwtUtil = new JwtUtil();
    private final AuthServiceImpl authService = new AuthServiceImpl(jwtUtil);

    @Test
    public void testAuthenticateSuccess() {
        AuthRequest request = new AuthRequest("user", "pass");

        Mono<AuthResponse> result = authService.authenticate(request);

        StepVerifier.create(result)
                .assertNext(response -> {
                    assertNotNull(response.getToken());
                    assertTrue(jwtUtil.validateToken(response.getToken()));
                })
                .verifyComplete();
    }

    @Test
    public void testAuthenticateFail() {
        AuthRequest request = new AuthRequest("bad", "credentials");

        Mono<AuthResponse> result = authService.authenticate(request);

        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }
}
