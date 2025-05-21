package com.clinica.services.v1.cita;

import com.clinica.services.v1.cita.domain.Cita;
import com.clinica.services.v1.cita.dto.CitaRequest;
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
        // Construye el DTO que recibe el servicio
        CitaRequest citaRequest = new CitaRequest("123", "1234", "Fernando Tapia", "Cardiología", "Valeria Bauta", LocalDateTime.of(2025, 5, 20, 10, 0), "Clinico");

        // Construye la entidad que se espera guardar
        Cita citaEntidad = new Cita("123", "1234", "Fernando Tapia", "Cardiología", "Valeria Bauta", LocalDateTime.of(2025, 5, 20, 10, 0), "Clinico");
        ;

        // Simula el validador (que pase sin error)
        Mockito.when(validadorCita.validar(Mockito.any(Cita.class)))
                .thenReturn(Mono.empty());

        // Inyecta la lista de validadores en el servicio (como haces)
        citaService = new CitaService(citaRepository, List.of(validadorCita));

        // Simula el guardado en repositorio y que retorne la entidad con ID generado
        Mockito.when(citaRepository.save(Mockito.any(Cita.class)))
                .thenReturn(Mono.just(citaEntidad));

        // Opcional: simular el mapeo de entidad a response (si tienes un CitaMapper)
        // Aquí suponemos que el mapper es estático o inline, o que el método registrar hace el mapeo

        StepVerifier.create(citaService.registrar(citaRequest))
                .expectNextMatches(response ->
                        response.getStatusCode().is2xxSuccessful() &&
                                response.getBody() != null &&
                                response.getBody().getId().equals("1")
                )
                .verifyComplete();
    }
}