package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "enfermedad")
@Data
public class Enfermedad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enfermedad_id")
    private Long enfermedadId;

    @ManyToOne
    @JoinColumn(name = "vaca_id", nullable = false)
    private Vaca vaca;

    @Column(name = "enfermedad_desc")
    private String enfermedadDesc;

    @Column(name = "enfermedad_init", nullable = false)
    private LocalDate enfermedadInit;

    @Column(name = "enfermedad_end")
    private LocalDate enfermedadEnd;
}
