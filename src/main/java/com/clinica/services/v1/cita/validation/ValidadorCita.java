package com.clinica.services.v1.cita.validation;

import com.clinica.services.v1.cita.domain.Cita;
import reactor.core.publisher.Mono;

public interface ValidadorCita {
    Mono<Void> validar(Cita cita);
}
