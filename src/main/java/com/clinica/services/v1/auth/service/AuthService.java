package com.clinica.services.v1.auth.service;

import com.clinica.services.v1.auth.dto.AuthRequest;
import com.clinica.services.v1.auth.dto.AuthResponse;
import reactor.core.publisher.Mono;

public interface AuthService {
    Mono<AuthResponse> authenticate(AuthRequest request);
}
