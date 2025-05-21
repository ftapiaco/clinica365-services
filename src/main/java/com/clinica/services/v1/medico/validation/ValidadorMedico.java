package com.clinica.services.v1.medico.validation;

import com.clinica.services.v1.medico.domain.Medico;
import reactor.core.publisher.Mono;

public interface ValidadorMedico {
    Mono<Void> validar(Medico medico);
}
