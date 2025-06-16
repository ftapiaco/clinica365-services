package com.clinica.services.v1.cita.mapper;

import com.clinica.services.v1.cita.domain.Cita;
import com.clinica.services.v1.cita.dto.CitaRequest;
import com.clinica.services.v1.cita.dto.CitaResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CitaMapper {
    private static final Logger logger = LoggerFactory.getLogger(CitaMapper.class);

    // 👇 Evita que se instancie esta clase
    private CitaMapper() {
        throw new UnsupportedOperationException("Esta clase no puede ser instanciada");
    }

    public static Cita toEntity(CitaRequest request) {
        logger.info("request {}", request);
        Cita cita = new Cita();
        cita.setIdMedico(request.getIdMedico());
        cita.setPacienteNombre(request.getPacienteNombre());
        cita.setEspecialidad(request.getEspecialidad());
        cita.setMedico(request.getMedico());
        cita.setFechaHora(request.getFechaHora());
        cita.setMotivo(request.getMotivo());
        logger.info("toEntity {}", cita);
        return cita;
    }

    public static CitaResponse toResponse(Cita cita) {
        logger.info("Cita {}", cita);
        CitaResponse response = new CitaResponse();
        response.setId(cita.getId());
        response.setPacienteNombre(cita.getPacienteNombre());
        response.setEspecialidad(cita.getEspecialidad());
        response.setMedico(cita.getMedico());
        response.setFecha(cita.getFechaHora());
        response.setMotivo(cita.getMotivo());
        logger.info("toResponse {}", response);
        return response;
    }
}

