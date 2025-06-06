package com.clinica.services.v1.medico.mapper;
import com.clinica.services.v1.medico.domain.Medico;
import com.clinica.services.v1.medico.dto.MedicoRequest;
import com.clinica.services.v1.medico.dto.MedicoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MedicoMapper {
    private static final Logger logger = LoggerFactory.getLogger(MedicoMapper.class);
    public static Medico toEntity(MedicoRequest request) {
        logger.info("request {}",request);
        Medico medico = new Medico();
        medico.setId(request.getIdMedico());
        medico.setNombre(request.getNombre());
        medico.setEspecialidad(request.getEspecialidad());
        medico.setActivo(request.isActivo());

        logger.info("toEntity {}",medico);
        return medico;

    }

    public static MedicoResponse toResponse(Medico medico) {
        logger.info("Cita {}",medico);
        MedicoResponse response = new MedicoResponse();
        response.setIdMedico(medico.getId());
        response.setNombre(medico.getNombre());
        response.setEspecialidad(medico.getEspecialidad());
        response.setActivo(medico.isActivo());
        logger.info("toResponse {}",response);
        return response;
    }
}
