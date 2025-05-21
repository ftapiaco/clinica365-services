package com.clinica.services.v1.cita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaResponse {
    private String id;
    private String fecha;
    private String especialidad;
    private String medico;
}
