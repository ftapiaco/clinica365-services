package com.clinica.services.v1.cita.service;

import com.clinica.services.v1.cita.domain.Cita;
import com.clinica.services.v1.cita.dto.CitaRequest;
import com.clinica.services.v1.cita.dto.CitaResponse;
import com.clinica.services.v1.cita.mapper.CitaMapper;
import com.clinica.services.v1.cita.repository.CitaRepository;
import com.clinica.services.v1.cita.validation.ValidadorCita;
import com.clinica.services.v1.exception.exceptions.BadRequestException;
import com.clinica.services.v1.exception.exceptions.GenericException;
import com.clinica.services.v1.exception.exceptions.ResourceNotFoundException;
import com.clinica.services.v1.exception.exceptions.UnauthorizedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import com.clinica.services.v1.config.utils.*;
/*
Qué hace:
    Contiene la lógica de negocio para gestionar citas (listar, registrar, buscar por ID, etc.). Usa el repositorio y devuelve objetos Mono/Flux.

    Principio SOLID aplicado:

    S: Se encarga exclusivamente de la lógica de negocio.

    O (Open/Closed): Puedes extender el servicio sin modificarlo (por ejemplo, añadiendo validaciones).

    D: Depende de interfaces (CitaRepository) y no implementaciones concretas.
 */
@Service
public class CitaService {
    private static final Logger logger = LoggerFactory.getLogger(CitaService.class);

    private final CitaRepository repository;
    private final List<ValidadorCita> validadores;

    @Autowired
    private ObjectMapper objectMapper;

    public CitaService(CitaRepository repository, List<ValidadorCita> validadores) {
        this.repository = repository;
        this.validadores = validadores;
    }

    public Mono<ResponseEntity<CitaResponse>> registrar(CitaRequest citaRequest) {
        Cita cita = CitaMapper.toEntity(citaRequest); // primero convertir
        logger.info("dato enviado a validar {}", util.toJsonString(citaRequest));
        return validar(cita) // validar la entidad
                .then(repository.save(cita)) // guardar después de validar
                .map(CitaMapper::toResponse).log() // mapear respuesta
                .map(savedResponse -> ResponseEntity.status(HttpStatus.CREATED).body(savedResponse))
                .onErrorResume(ResourceNotFoundException.class, ex ->
                        Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()))
                .onErrorResume(Exception.class, ex ->
                        Mono.error(new RuntimeException("Error al registrar la cita: " + ex.getMessage())));
    }

    public Mono<ResponseEntity<List<CitaResponse>>> listar() {
        return repository.findAll()
                .doOnNext(cita -> logger.info("Cita encontrada: {}", cita))
                .map(CitaMapper::toResponse)  // mapeo por cada entidad
                .collectList()
                .doOnSuccess(citas -> logger.info("Total citas encontradas: {}", util.toJsonString(citas)))
                .map(ResponseEntity::ok)
                .onErrorMap(ex -> {
                    // Si ya es una excepción personalizada, no la toques
                    if (ex instanceof BadRequestException ||
                            ex instanceof UnauthorizedException ||
                            ex instanceof GenericException) {
                        return ex;
                    }
                    // Aquí sí puedes envolver otras excepciones como errores generales
                    return new GenericException("Error General: " + ex.getMessage());
                });
    }

    public Mono<ResponseEntity<CitaResponse>> buscarPorId(String id) {
        return repository.findById(id)
                .map(CitaMapper::toResponse)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Cita no encontrada con ID: " + id)))
                .onErrorMap(ex -> {
                    // Si ya es una excepción personalizada, no la toques
                    if (ex instanceof BadRequestException ||
                            ex instanceof UnauthorizedException ||
                            ex instanceof GenericException) {
                        return ex;
                    }
                    // Aquí sí puedes envolver otras excepciones como errores generales
                    return new GenericException("Error General: " + ex.getMessage());
                });
    }

    private Mono<Void> validar(Cita cita) {
        return Flux.fromIterable(validadores)
                .flatMap(validador -> validador.validar(cita))
                .then();
    }




}
