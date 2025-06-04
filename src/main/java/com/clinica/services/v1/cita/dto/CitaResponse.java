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
    @Schema(description = "Identificador único de la cita", example = "12345665")
    private String id;

    @Schema(description = "Fecha y hora de la cita", example = "2025-06-04T10:30:00")
    private LocalDateTime fecha;

    @Schema(description = "Nombre del paciente", example = "Fernando Tapia Coronado")
    private String pacienteNombre;

    @Schema(description = "Especialidad médica", example = "Cardiología")
    private String especialidad;

    @Schema(description = "Nombre del médico asignado", example = "Dra. Laura Sánchez")
    private String medico;

    @Schema(description = "Motivo de la cita", example = "Consulta de control")
    private String motivo;
}
