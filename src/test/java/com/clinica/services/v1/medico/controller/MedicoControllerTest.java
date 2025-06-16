package com.clinica.services.v1.medico.controller;

import com.clinica.services.v1.auth.jwt.JwtUtil;
import com.clinica.services.v1.config.ErrorCatalogProperties;
import com.clinica.services.v1.config.TestSecurityConfig;
import com.clinica.services.v1.medico.dto.MedicoRequest;
import com.clinica.services.v1.medico.dto.MedicoResponse;
import com.clinica.services.v1.medico.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(MedicoController.class)
@Import(TestSecurityConfig.class) // ✅ Usa la misma config que en CitaControllerTest
class MedicoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private MedicoService service;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private ErrorCatalogProperties errorCatalogProperties;

    @Test
    void testCrearMedico_201() {
        MedicoRequest request = new MedicoRequest();
        request.setNombre("Dra. Ana");
        request.setEspecialidad("Pediatría");

        MedicoResponse response = new MedicoResponse();
        response.setIdMedico("med123");
        response.setNombre("Dra. Ana");
        response.setEspecialidad("Pediatría");

        when(service.crear(any(MedicoRequest.class)))
                .thenReturn(Mono.just(ResponseEntity.status(HttpStatus.CREATED).body(response)));

        webTestClient.put()
                .uri("/api/v1/medico")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.idMedico").isEqualTo("med123")
                .jsonPath("$.nombre").isEqualTo("Dra. Ana");
    }

    @Test
    void testObtenerMedicoPorId_200() {
        MedicoResponse response = new MedicoResponse();
        response.setIdMedico("med123");
        response.setNombre("Dra. Ana");
        response.setEspecialidad("Pediatría");

        when(service.obtenerPorId("med123"))
                .thenReturn(Mono.just(ResponseEntity.ok(response)));

        webTestClient.get()
                .uri("/api/v1/medico/med123")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.idMedico").isEqualTo("med123")
                .jsonPath("$.nombre").isEqualTo("Dra. Ana");
    }

    @Test
    void testListarMedicos_200() {
        MedicoResponse medico = new MedicoResponse();
        medico.setIdMedico("1");
        medico.setNombre("Dr. Juan");
        medico.setEspecialidad("Cardiología");

        when(service.listarActivos())
                .thenReturn(Mono.just(ResponseEntity.ok(List.of(medico))));

        webTestClient.get()
                .uri("/api/v1/medico")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].nombre").isEqualTo("Dr. Juan");
    }
}
