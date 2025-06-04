package com.clinica.services.v1.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Respuesta con el token JWT generado al autenticarse correctamente")
public class AuthResponse {

    @Schema(description = "Token JWT generado para el usuario autenticado", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;
}