package com.clinica.services.v1.cita.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitaRequest {
    private String fecha;
    private String idMedico;
    private String idPaciente;
}
