package com.clinica.services.v1.cita.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
/*
    Qué hace:
    Es tu modelo de dominio. Representa la entidad Cita (una cita médica) con sus atributos: id, fecha, médico, paciente, etc.
    Principio SOLID aplicado:
    S (Single Responsibility): Solo tiene una responsabilidad: representar una cita.

 */


@Data
@NoArgsConstructor          // Constructor sin parámetros
@AllArgsConstructor         // Constructor con todos los parámetros
@Document(collection = "citas")
public class Cita {
    @Id
    private String id;
    private String idMedico;
    private String pacienteNombre;
    private String especialidad;
    private String medico;
    private LocalDateTime fechaHora;
    private String motivo;

}