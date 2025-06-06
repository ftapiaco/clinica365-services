package com.clinica.services.v1.exception;


import com.clinica.services.v1.config.ErrorCatalogProperties;
import com.clinica.services.v1.exception.dto.ErrorResponse;
import com.clinica.services.v1.exception.exceptions.BadRequestException;
import com.clinica.services.v1.exception.exceptions.GenericException;
import com.clinica.services.v1.exception.exceptions.ResourceNotFoundException;
import com.clinica.services.v1.exception.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import org.springframework.core.codec.DecodingException;
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
        // Prioriza el mensaje de la excepción personalizada
        String message = ex.getMessage(); // <- este mensaje viene del "Cita no encontrada con ID: ..."

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                message != null ? message : errorCatalogProperties.getErrorMessages().get("resourceNotFound")
        );
        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(error));
    }


    @ExceptionHandler(GenericException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleGenericException(Exception ex) {
        String message = errorCatalogProperties.getErrorMessages().get("internalServerError");
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                message != null ? message : ex.getMessage()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error));
    }

    @ExceptionHandler(BadRequestException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleBadRequestException(Exception ex) {
        String message = errorCatalogProperties.getErrorMessages().get("badRequestError");
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Solicitud incorrecta",
                message != null ? message : ex.getMessage()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleUnauthorizedException(Exception ex) {
        String message = errorCatalogProperties.getErrorMessages().get("unauthorizedError");
        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.UNAUTHORIZED.value(),
                "Unauthorized",
                message != null ? message : ex.getMessage()
        );
        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleUnreadableBody(HttpMessageNotReadableException ex) {
        String message = errorCatalogProperties.getErrorMessages().get("badRequestError");

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                message != null ? message : "El cuerpo de la solicitud está vacío o es inválido"
        );

        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error));
    }

    @ExceptionHandler(DecodingException.class)
    public Mono<ResponseEntity<ErrorResponse>> handleDecoding(DecodingException ex) {
        String message = errorCatalogProperties.getErrorMessages().get("badRequestError");

        ErrorResponse error = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                message != null ? message : "No se pudo decodificar el cuerpo de la solicitud"
        );

        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error));
    }

}