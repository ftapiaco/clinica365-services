package com.clinica.services.v1.auth.controller;

import com.clinica.services.v1.auth.dto.AuthRequest;
import com.clinica.services.v1.auth.dto.AuthResponse;
import com.clinica.services.v1.auth.service.AuthService;
import com.clinica.services.v1.exception.exceptions.UnauthorizedException;
import com.clinica.services.v1.exception.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import com.clinica.services.v1.config.utils.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticación", description = "Operaciones relacionadas con la autenticación del JWT")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(
            summary = "Iniciar sesión",
            description = "Permite autenticar al usuario y generar un token JWT si las credenciales son válidas.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticación exitosa. Token JWT generado.",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Credenciales inválidas",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    public Mono<ResponseEntity<AuthResponse>> login(@Valid @RequestBody AuthRequest authRequest, ServerHttpRequest request) {
        logger.info("Inicio login");
        //logger.info("Request obtenido AuthRequest : {}", util.toJsonString( authRequest));
        if (logger.isInfoEnabled()) {
            logger.info("Request obtenido AuthRequest : {}", Util.toJsonString(authRequest));
        }
        return authService.authenticate(authRequest)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(new UnauthorizedException("Credenciales inválidas")))
                .onErrorResume(UnauthorizedException.class, ex -> {
                    logger.error("Unauthorized exception atrapada en login: {}", ex.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
                });

    }
}
