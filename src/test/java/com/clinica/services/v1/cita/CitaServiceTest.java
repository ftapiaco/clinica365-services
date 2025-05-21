package com.clinica.services.v1.cita;

import com.clinica.services.v1.cita.domain.Cita;
import com.clinica.services.v1.cita.repository.CitaRepository;
import com.clinica.services.v1.cita.service.CitaService;
import com.clinica.services.v1.cita.validation.ValidadorCita;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private ValidadorCita validadorCita;

    @InjectMocks
    private CitaService citaService;

    @Test
    void testCrearCita() {
        Cita cita = new Cita("1", "123", "Fernando Tapia", LocalDateTime.of(2025, 5, 20, 10, 0), "Cardiología");

        // Mock del comportamiento del validador
        Mockito.when(validadorCita.validar(Mockito.any(Cita.class)))
                .thenReturn(Mono.empty()); // Simula validación exitosa

        // Construimos el service con la lista de validadores
        citaService = new CitaService(citaRepository, List.of(validadorCita));

        Mockito.when(citaRepository.save(Mockito.any(Cita.class)))
                .thenReturn(Mono.just(cita));

        StepVerifier.create(citaService.registrar(cita))
                .expectNextMatches(c -> c.getBody().getId().equals("1"))
                .verifyComplete();
    }
}
