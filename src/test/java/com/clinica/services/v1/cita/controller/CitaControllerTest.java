package com.clinica.services.v1.cita.controller;

import com.clinica.services.v1.auth.jwt.JwtUtil;
import com.clinica.services.v1.cita.dto.CitaRequest;
import com.clinica.services.v1.cita.dto.CitaResponse;
import com.clinica.services.v1.cita.service.CitaService;
import com.clinica.services.v1.config.ErrorCatalogProperties;
import com.clinica.services.v1.config.TestSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(CitaController.class)
@Import(TestSecurityConfig.class)
class CitaControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CitaService service;

    @MockBean
    private ErrorCatalogProperties errorCatalogProperties;

    @MockBean
    private JwtUtil jwtUtil;

    @Test
    void crearCita_201() {
        CitaRequest request = new CitaRequest();
        request.setFechaHora(LocalDateTime.now().plusDays(1));
        request.setPacienteNombre("Juan Perez");
        request.setEspecialidad("Cardiología");
        request.setMedico("Dr. López");
        request.setMotivo("Consulta");

        CitaResponse response = new CitaResponse();
        response.setId("abc123");
        response.setFecha(request.getFechaHora());
        response.setPacienteNombre(request.getPacienteNombre());
        response.setEspecialidad(request.getEspecialidad());
        response.setMedico(request.getMedico());
        response.setMotivo(request.getMotivo());

        when(service.registrar(any(CitaRequest.class)))
                .thenReturn(Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(response)));

        webTestClient.post()
                .uri("/api/v1/citas")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo("abc123")
                .jsonPath("$.pacienteNombre").isEqualTo("Juan Perez");
    }

}
