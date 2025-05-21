package com.clinica.services.v1.cita.controller;

//import com.clinica.services.v1.cita.domain.Cita;
import com.clinica.services.v1.cita.dto.CitaRequest;
import com.clinica.services.v1.cita.dto.CitaResponse;
import com.clinica.services.v1.cita.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


import java.util.List;
/*
    Qué hace:
    Expone los endpoints HTTP (/citas, /citas/{id}, etc.) y delega al servicio para procesar las solicitudes.

    Principio SOLID aplicado:

    S: Solo maneja la lógica relacionada con la capa web.

    D: Depende del servicio, que es una abstracción de la lógica de negocio.
 */
@RestController
@RequestMapping("/api/v1")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping(value = "/citas")
    public Mono<ResponseEntity<CitaResponse>> crear(@RequestBody CitaRequest cita) {
        return service.registrar(cita);
    }

    @GetMapping("/citas")
    public Mono<ResponseEntity<List<CitaResponse>>> listar() {
        return service.listar();
    }

    @GetMapping("/citas/{id}")
    public Mono<ResponseEntity<CitaResponse>> obtener(@PathVariable String id) {
        return service.buscarPorId(id);
    }
}
