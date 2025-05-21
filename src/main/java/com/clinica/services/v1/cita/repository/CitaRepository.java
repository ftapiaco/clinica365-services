package com.clinica.services.v1.cita.repository;

import com.clinica.services.v1.cita.domain.Cita;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
/*
    Qué hace:
    Es una interfaz que extiende ReactiveCrudRepository, conectando con la base de datos MongoDB de forma reactiva.

    Principio SOLID aplicado:

    D (Dependency Inversion): Tu capa de servicio depende de la abstracción (el repositorio), no de la implementación.

    S (Single Responsibility): Solo se encarga del acceso a datos.
 */
public interface CitaRepository extends ReactiveMongoRepository<Cita, String> {
    Flux<Cita> findByIdMedicoAndFechaHoraBetween(String idMedico, LocalDateTime desde, LocalDateTime hasta);
}
