package com.clinica.services.v1.medico.repository;

import com.clinica.services.v1.medico.domain.Medico;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface MedicoRepository extends ReactiveMongoRepository<Medico, String> {
    Flux<Medico> findByActivoTrue();
}
