package com.clinica.services.v1.medico.service;

import com.clinica.services.v1.exception.exceptions.ResourceNotFoundException;
import com.clinica.services.v1.medico.domain.Medico;
import com.clinica.services.v1.medico.dto.MedicoRequest;
import com.clinica.services.v1.medico.dto.MedicoResponse;
import com.clinica.services.v1.medico.mapper.MedicoMapper;
import com.clinica.services.v1.medico.repository.MedicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicoServiceTest {

    @Mock
    private MedicoRepository repository;

    @InjectMocks
    private MedicoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listarActivos_deberiaRetornarLista() {
        Medico medico = new Medico();
        medico.setId("1");
        medico.setNombre("Dr. Juan");
        medico.setEspecialidad("Cardiología");
        medico.setActivo(true);

        when(repository.findByActivoTrue()).thenReturn(Flux.just(medico));

        Mono<ResponseEntity<List<MedicoResponse>>> resultado = service.listarActivos();

        ResponseEntity<List<MedicoResponse>> response = resultado.block();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        assertEquals("Dr. Juan", response.getBody().get(0).getNombre());
    }

    @Test
    void crear_deberiaRetornarMedicoCreado() {
        MedicoRequest request = new MedicoRequest();
        request.setNombre("Dra. Ana");
        request.setEspecialidad("Pediatría");
        request.setActivo(true);

        Medico medicoEntity = MedicoMapper.toEntity(request);
        medicoEntity.setId("2");

        when(repository.save(any(Medico.class))).thenReturn(Mono.just(medicoEntity));

        Mono<ResponseEntity<MedicoResponse>> resultado = service.crear(request);

        ResponseEntity<MedicoResponse> response = resultado.block();
        assertNotNull(response);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Dra. Ana", response.getBody().getNombre());
    }

    @Test
    void obtenerPorId_conIdExistente() {
        Medico medico = new Medico();
        medico.setId("1");
        medico.setNombre("Dr. Juan");
        medico.setEspecialidad("Cardiología");
        medico.setActivo(true);

        when(repository.findById("1")).thenReturn(Mono.just(medico));

        Mono<ResponseEntity<MedicoResponse>> resultado = service.obtenerPorId("1");

        ResponseEntity<MedicoResponse> response = resultado.block();
        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Dr. Juan", response.getBody().getNombre());
    }

    @Test
    void obtenerPorId_conIdInexistente() {
        when(repository.findById("999")).thenReturn(Mono.empty());

        Mono<ResponseEntity<MedicoResponse>> resultado = service.obtenerPorId("999");

        Exception ex = assertThrows(ResourceNotFoundException.class, resultado::block);
        assertTrue(ex.getMessage().contains("Cita no encontrada con ID: 999"));
    }
}
