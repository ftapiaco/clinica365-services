package com.clinica.services.v1.cita.validation;

import com.clinica.services.v1.cita.domain.Cita;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class ValidarFechaFutura implements ValidadorCita {

    @Override
    public Mono<Void> validar(Cita cita) {
        if (cita.getFechaHora().isBefore(LocalDateTime.now())) {
            return Mono.error(new IllegalArgumentException("La fecha debe ser futura"));
        }
        return Mono.empty();
    }
}
