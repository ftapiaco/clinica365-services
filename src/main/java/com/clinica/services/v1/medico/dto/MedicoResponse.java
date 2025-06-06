package com.clinica.services.v1.medico.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor          // Constructor sin parámetros.
@AllArgsConstructor         // Constructor con todos los parámetros
public class MedicoResponse {
    private String idMedico;
    private String nombre;
    private String especialidad;
    private boolean activo;
}
