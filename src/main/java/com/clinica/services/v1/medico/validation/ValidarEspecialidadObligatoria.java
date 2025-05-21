package com.clinica.services.v1.medico.validation;

import com.clinica.services.v1.medico.domain.Medico;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ValidarEspecialidadObligatoria implements ValidadorMedico {

    @Override
    public Mono<Void> validar(Medico medico) {
        if (medico.getEspecialidad() == null || medico.getEspecialidad().isBlank()) {
            return Mono.error(new IllegalArgumentException("La especialidad es obligatoria"));
        }
        return Mono.empty();
    }
}
