package com.clinica.services.v1.cita.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor          // Constructor sin parámetros
@AllArgsConstructor         // Constructor con todos los parámetros
@Schema(description = "Representa la variables de entrada de la cita")
public class CitaRequest {
    @Schema(example = "123415646")
    private String id;
    @Schema(example = "12341546")
    private String idMedico;
    @Schema(example = "Fernando Tapia C.")
    private String pacienteNombre;
    @Schema(example = "Cardiologia")
    private String especialidad;
    @Schema(example = "Jose Luis A.")
    private String medico;
    @Schema(example = "2025-05-21T10:30:00")
    private LocalDateTime fechaHora;
    @Schema(example = "Salud")
    private String motivo;

}
