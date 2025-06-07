package com.clinica.services.v1.cita.service;

import com.clinica.services.v1.cita.domain.Cita;
import com.clinica.services.v1.cita.dto.CitaRequest;
import com.clinica.services.v1.cita.dto.CitaResponse;
import com.clinica.services.v1.cita.repository.CitaRepository;
import com.clinica.services.v1.cita.validation.ValidadorCita;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CitaServiceTest {

    private CitaRepository citaRepository;

    private ValidadorCita validadorCita;

    private CitaService citaService;

    @BeforeEach
    void setup() {
        citaRepository = Mockito.mock(CitaRepository.class);
        validadorCita = Mockito.mock(ValidadorCita.class);
        citaService = new CitaService(citaRepository, List.of(validadorCita));
    }

    @Test
    void testRegistrarCita_Success() {
        CitaRequest request = new CitaRequest(
                "123",
                "1234",
                "Fernando Tapia",
                "Cardiología",
                "Valeria Bauta",
                LocalDateTime.of(2025, 5, 20, 10, 0),
                "Clinico"
        );

        Cita citaEntidad = new Cita(
                "123",
                "1234",
                "Fernando Tapia",
                "Cardiología",
                "Valeria Bauta",
                LocalDateTime.of(2025, 5, 20, 10, 0),
                "Clinico"
        );

        Mockito.when(validadorCita.validar(Mockito.any(Cita.class)))
                .thenReturn(Mono.empty());

        Mockito.when(citaRepository.save(Mockito.any(Cita.class)))
                .thenReturn(Mono.just(citaEntidad));

        Mono<ResponseEntity<CitaResponse>> result = citaService.registrar(request);

        StepVerifier.create(result)
                .expectNextMatches(responseEntity -> {
                    CitaResponse body = responseEntity.getBody();
                    return responseEntity.getStatusCode() == HttpStatus.CREATED
                            && body != null
                            && "123".equals(body.getId())
                            && "Cardiología".equals(body.getEspecialidad());
                })
                .verifyComplete();
    }

    @Test
    void testBuscarPorId_Success() {
        Cita citaEntidad = new Cita(
                "123",
                "1234",
                "Fernando Tapia",
                "Cardiología",
                "Valeria Bauta",
                LocalDateTime.of(2025, 5, 20, 10, 0),
                "Clinico"
        );

        Mockito.when(citaRepository.findById("123"))
                .thenReturn(Mono.just(citaEntidad));

        StepVerifier.create(citaService.buscarPorId("123"))
                .expectNextMatches(resp -> resp.getStatusCode() == HttpStatus.OK &&
                        resp.getBody() != null &&
                        "123".equals(resp.getBody().getId()))
                .verifyComplete();
    }

    @Test
    void testRegistrarCita_ValidadorInvocado() {
        CitaRequest request = new CitaRequest("123", "1234", "Fernando Tapia", "Cardiología", "Valeria Bauta",
                LocalDateTime.of(2025, 5, 20, 10, 0), "Clinico");

        Mockito.when(validadorCita.validar(Mockito.any(Cita.class))).thenReturn(Mono.empty());
        Mockito.when(citaRepository.save(Mockito.any(Cita.class))).thenReturn(Mono.just(new Cita()));

        citaService.registrar(request).block();

        Mockito.verify(validadorCita, Mockito.times(1)).validar(Mockito.any(Cita.class));
    }
}
