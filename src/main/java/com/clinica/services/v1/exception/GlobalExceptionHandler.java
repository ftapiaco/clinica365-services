package com.clinica.services.v1.exception;


import com.clinica.services.v1.config.ErrorCatalogProperties;
import com.clinica.services.v1.exception.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;
/*
    Qué hace:
    Maneja excepciones de forma centralizada usando @RestControllerAdvice. Por ejemplo, si lanzas una ResourceNotFoundException, la captura y devuelve una respuesta con 404.

    Principio SOLID aplicado:

    S: Se encarga exclusivamente del manejo de errores.

    O: Puedes agregar más métodos para capturar diferentes errores sin modificar los existentes.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ErrorCatalogProperties errorCatalogProperties;

    @ExceptionHandler(ResourceNotFoundException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleNotFound(ResourceNotFoundException ex) {
        String message = errorCatalogProperties.getErrorMessages().get("resourceNotFound");
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                message != null ? message : ex.getMessage()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
        String message = errorCatalogProperties.getErrorMessages().get("internalServerError");
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                message != null ? message : ex.getMessage()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error));
    }
}