package com.clinica.services.v1.auth.jwt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    private JwtUtil jwtUtil;
    private JwtAuthenticationFilter filter;

    @BeforeEach
    void setup() {
        jwtUtil = new JwtUtil();
        filter = new JwtAuthenticationFilter(jwtUtil);
    }

    @Test
    void testFilter_withValidToken_shouldContinueChain() {
        String token = jwtUtil.generateToken("testuser");

        MockServerHttpRequest request = MockServerHttpRequest.get("/api/protected")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
        ServerWebExchange exchange = MockServerWebExchange.from(request);

        WebFilterChain chain = mock(WebFilterChain.class);
        when(chain.filter(exchange)).thenReturn(Mono.empty());

        Mono<Void> result = filter.filter(exchange, chain);

        StepVerifier.create(result).verifyComplete();
        verify(chain, times(1)).filter(exchange);
    }

    @Test
    void testFilter_withNoToken_shouldContinueChain() {
        MockServerHttpRequest request = MockServerHttpRequest.get("/api/protected")
                .build();
        ServerWebExchange exchange = MockServerWebExchange.from(request);

        WebFilterChain chain = mock(WebFilterChain.class);
        when(chain.filter(exchange)).thenReturn(Mono.empty());

        Mono<Void> result = filter.filter(exchange, chain);

        StepVerifier.create(result).verifyComplete();
        verify(chain, times(1)).filter(exchange);
    }

    @Test
    void testFilter_withInvalidToken_shouldContinueChain() {
        MockServerHttpRequest request = MockServerHttpRequest.get("/api/protected")
                .header(HttpHeaders.AUTHORIZATION, "Bearer invalid.token.here")
                .build(); // 👈 aquí estaba el problema

        ServerWebExchange exchange = MockServerWebExchange.from(request);

        WebFilterChain chain = mock(WebFilterChain.class);
        when(chain.filter(exchange)).thenReturn(Mono.empty());

        Mono<Void> result = filter.filter(exchange, chain);

        StepVerifier.create(result).verifyComplete();
        verify(chain, times(1)).filter(exchange);
    }


    @Test
    void testFilter_forPublicAuthPath_shouldSkipFilter() {
        MockServerHttpRequest request = MockServerHttpRequest.get("/auth/login").build();
        ServerWebExchange exchange = MockServerWebExchange.from(request);

        WebFilterChain chain = mock(WebFilterChain.class);
        when(chain.filter(exchange)).thenReturn(Mono.empty());

        Mono<Void> result = filter.filter(exchange, chain);

        StepVerifier.create(result).verifyComplete();
        verify(chain, times(1)).filter(exchange);
    }
}
