package com.clinica.services.v1.cita.controller;

//import com.clinica.services.v1.cita.domain.Cita;
import com.clinica.services.v1.cita.dto.CitaRequest;
import com.clinica.services.v1.cita.dto.CitaResponse;
import com.clinica.services.v1.cita.service.CitaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cita", description = "Gestión de citas medicas")
public class CitaController {

    private final CitaService service;

    public CitaController(CitaService service) {
        this.service = service;
    }

    @PostMapping(value = "/citas")
    @Operation(summary = "Crea cita medica", description = "Retorna la cita creada")
    @ApiResponse(responseCode = "201", description = "Cita creada correctamente")
    public Mono<ResponseEntity<CitaResponse>> crear(@RequestBody CitaRequest cita) {
        return service.registrar(cita);
    }

    @GetMapping("/citas")
    @Operation(summary = "Lista las citas medicas", description = "Retorna la lista de citas")
    @ApiResponse(responseCode = "200", description = "Cita creada correctamente")
    public Mono<ResponseEntity<List<CitaResponse>>> listar() {
        return service.listar();
    }

    @GetMapping("/citas/{id}")
    @Operation(summary = "Lista las cita medica", description = "Retorna la cita medica por su Id.")
    @ApiResponse(responseCode = "200", description = "Cita obtenida correctamente")
    public Mono<ResponseEntity<CitaResponse>> obtener(@PathVariable String id) {
        return service.buscarPorId(id);
    }
}
