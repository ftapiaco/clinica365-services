package com.clinica.services.v1.exception.exceptions;
/*
    Qué hace:
    Es una excepción personalizada para cuando no se encuentra una cita u otro recurso.

    Principio SOLID aplicado:

    S: Solo tiene la responsabilidad de representar un error de "recurso no encontrado".
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}