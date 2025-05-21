package com.clinica.services.v1.medico.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "medicos")
public class Medico {
    @Id
    private String id;
    private String nombre;
    private String especialidad;
    private boolean activo;

}