package com.clinica.services.v1.cita.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Representa la variables de salida de la cita")
public class CitaResponse {
    @Schema(example = "123415646")
    private String id;
    @Schema(example = "2025-05-21T10:30:00")
    private LocalDateTime fecha;
    @Schema(example = "Fernando Tapia C.")
    private String PacienteNombre;
    @Schema(example = "Cardiologia")
    private String especialidad;
    @Schema(example = "Jose Luis A.")
    private String medico;
    @Schema(example = "Salud")
    private String motivo;
}
