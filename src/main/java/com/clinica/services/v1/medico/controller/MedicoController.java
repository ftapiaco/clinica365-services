package com.clinica.services.v1.medico.controller;

import com.clinica.services.v1.auth.controller.AuthController;
import com.clinica.services.v1.medico.domain.Medico;
import com.clinica.services.v1.medico.service.MedicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MedicoController {
    private static final Logger logger = LoggerFactory.getLogger(MedicoController.class);

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    @GetMapping("/medico")
    @Operation(summary = "Listar médicos activos", description = "Retorna todos los médicos con estado activo")
    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente")
    public Mono<ResponseEntity<List<Medico>>> listar() {
        logger.info("ingreso");
        return service.listarActivos();
    }

    @PutMapping(value = "/medico")
    public Mono<ResponseEntity<Medico>> crear(@RequestBody Medico medico) {
        return service.crear(medico);
    }

    @GetMapping(value = "/medico/{id}")
    public Mono<ResponseEntity<Medico>> obtener(@PathVariable String id) {
        return service.obtenerPorId(id);
    }
}