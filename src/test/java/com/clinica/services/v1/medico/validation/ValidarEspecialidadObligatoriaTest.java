package com.clinica.services.v1.medico.validation;

import com.clinica.services.v1.medico.domain.Medico;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class ValidarEspecialidadObligatoriaTest {

    private final ValidarEspecialidadObligatoria validador = new ValidarEspecialidadObligatoria();

    @Test
    void debeLanzarExcepcionCuandoEspecialidadEsNula() {
        Medico medico = new Medico();
        medico.setEspecialidad(null);

        StepVerifier.create(validador.validar(medico))
                .expectErrorMatches(throwable ->
                        throwable instanceof IllegalArgumentException &&
                                throwable.getMessage().equals("La especialidad es obligatoria"))
                .verify();
    }

    @Test
    void debeLanzarExcepcionCuandoEspecialidadEstaVacia() {
        Medico medico = new Medico();
        medico.setEspecialidad("   ");

        StepVerifier.create(validador.validar(medico))
                .expectErrorMatches(throwable ->
                        throwable instanceof IllegalArgumentException &&
                                throwable.getMessage().equals("La especialidad es obligatoria"))
                .verify();
    }

    @Test
    void debePasarValidacionCuandoEspecialidadEsValida() {
        Medico medico = new Medico();
        medico.setEspecialidad("Cardiología");

        StepVerifier.create(validador.validar(medico))
                .verifyComplete();
    }
}
