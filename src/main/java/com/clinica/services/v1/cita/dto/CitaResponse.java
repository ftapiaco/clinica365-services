package com.clinica.services.v1.cita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaResponse {
    private String id;
    private LocalDateTime fecha;
    private String PacienteNombre;
    private String especialidad;
    private String medico;
    private String motivo;
}
