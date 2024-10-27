package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vaca_type")
@Data
public class VacaType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaca_type_id")
    private Long id;

    @Column(name = "vaca_type_desc", nullable = false)
    private String description;

    // Constructor vacío
    public VacaType() {}

    // Constructor con parámetros
    public VacaType(String description) {
        this.description = description;
    }
}
