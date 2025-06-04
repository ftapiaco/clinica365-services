package com.clinica.services.v1.auth.service.impl;

import com.clinica.services.v1.auth.dto.AuthRequest;
import com.clinica.services.v1.auth.dto.AuthResponse;
import com.clinica.services.v1.auth.jwt.JwtUtil;
import com.clinica.services.v1.auth.service.AuthService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtUtil jwtUtil;

    public AuthServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<AuthResponse> authenticate(AuthRequest request) {
        // Aquí va la lógica real de autenticación: buscar usuario, validar password, etc.
        // Aquí demo con usuario "user" y pass "pass"
        if ("user".equals(request.getUsername()) && "pass".equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return Mono.just(new AuthResponse(token));
        }
        return Mono.empty();  // vacío para indicar fallo de autenticación
    }
}
