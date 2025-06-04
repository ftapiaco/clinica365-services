package com.clinica.services.v1.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    @NotEmpty(message = "El nombre de usuario es obligatorio")
    @NotNull(message = "El nombre de usuario es obligatorio")
    @Schema(description = "Nombre de usuario", example = "user", required = true)

    private String username;
    @NotBlank(message = "La contraseña es obligatoria")
    @NotEmpty(message = "La contraseña es obligatoria")
    @NotNull(message = "La contraseña es obligatoria")
    @Schema(description = "Contraseña del usuario", example = "pass", required = true)
    private String password;
}
