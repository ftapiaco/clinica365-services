package com.clinica.services.v1.cita.validation;

import com.clinica.services.v1.cita.domain.Cita;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

class ValidarFechaFuturaTest {

    private final ValidarFechaFutura validador = new ValidarFechaFutura();

    @Test
    void validar_fechaFutura_Pasa() {
        Cita cita = new Cita();
        cita.setFechaHora(LocalDateTime.now().plusDays(1));

        StepVerifier.create(validador.validar(cita))
                .verifyComplete();
    }

    @Test
    void validar_fechaPasada_Error() {
        Cita cita = new Cita();
        cita.setFechaHora(LocalDateTime.now().minusDays(1));

        StepVerifier.create(validador.validar(cita))
                .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException
                        && throwable.getMessage().equals("La fecha debe ser futura"))
                .verify();
    }
}
