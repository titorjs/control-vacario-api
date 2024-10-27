package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vaca_status")
@Data
public class VacaStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaca_status_id")
    private Long id;

    @Column(name = "vaca_status_desc", nullable = false)
    private String description;

    // Constructor vacío
    public VacaStatus() {}

    // Constructor con parámetros
    public VacaStatus(String description) {
        this.description = description;
    }
}
