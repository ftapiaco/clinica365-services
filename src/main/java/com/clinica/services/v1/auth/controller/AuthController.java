package com.clinica.services.v1.auth.controller;

import com.clinica.services.v1.auth.dto.AuthRequest;
import com.clinica.services.v1.auth.jwt.JwtUtil;
import com.clinica.services.v1.cita.service.CitaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody AuthRequest auth) {
        logger.info("datos {}",auth);
        // Validación de usuario (dummy)
        if ("user".equals(auth.getUsername()) && "pass".equals(auth.getPassword())) {
            logger.info("dato 1 {} ",auth);
            String token = jwtUtil.generateToken(auth.getUsername());
            return Mono.just(ResponseEntity.ok(Map.of("token", token)));
        }
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}

