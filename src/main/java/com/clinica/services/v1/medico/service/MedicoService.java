package com.clinica.services.v1.medico.service;


import com.clinica.services.v1.exception.exceptions.ResourceNotFoundException;
import com.clinica.services.v1.medico.domain.Medico;
import com.clinica.services.v1.medico.repository.MedicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;


import com.clinica.services.v1.config.utils.*;

@Service
public class MedicoService {
    private static final Logger logger = LoggerFactory.getLogger(MedicoService.class);
    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public Mono<ResponseEntity<List<Medico>>> listarActivos() {
        return repository.findByActivoTrue()
                .collectList()
                .doOnNext(medico ->logger.info("Medicos encontrada: {}",util.toJsonString( medico)))
                .map(ResponseEntity::ok)
                .onErrorResume(Exception.class, ex ->
                        Mono.error(new RuntimeException("Error al listar la cita: " + ex.getMessage())))
                ;
    }


    public Mono<ResponseEntity<Medico>> crear(Medico medico) {
        return repository.save(medico).map(med -> new ResponseEntity<>(med, HttpStatus.CREATED))
                .onErrorResume(Exception.class, ex ->
                        Mono.error(new RuntimeException("Error al registrar el medico: " + ex.getMessage())));

    }

    public Mono<ResponseEntity<Medico>> obtenerPorId(String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Médico no encontrado con ID: " + id)))
                .onErrorResume(ResourceNotFoundException.class, ex ->
                        Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null))
                )
                .onErrorResume(Exception.class, ex ->
                        Mono.error(new RuntimeException("Error al buscar el medico: " + ex.getMessage())));
    }
}
