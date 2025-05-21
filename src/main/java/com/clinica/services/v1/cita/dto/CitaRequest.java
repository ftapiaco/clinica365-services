package com.clinica.services.v1.cita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor          // Constructor sin parámetros
@AllArgsConstructor         // Constructor con todos los parámetros
public class CitaRequest {
    private String id;
    private String idMedico;
    private String pacienteNombre;
    private String especialidad;
    private String medico;
    private LocalDateTime fechaHora;
    private String motivo;

}
