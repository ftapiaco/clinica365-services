package com.clinica.services.v1.cita.mapper;

import com.clinica.services.v1.cita.domain.Cita;
import com.clinica.services.v1.cita.dto.CitaResponse;

public class CitaMapper {

    public static CitaResponse citaResponseMap(Cita cita) {
        CitaResponse dto = new CitaResponse();
        dto.setId(cita.getId());
        dto.setFecha(cita.getFechaHora().toString());
        dto.setEspecialidad(cita.getEspecialidad());
        dto.setMedico(cita.getMedico());

        return dto;
    }
}
