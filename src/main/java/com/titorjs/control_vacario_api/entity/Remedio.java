package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "remedio")
@Data
public class Remedio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "remedio_id")
    private Long remedioId;

    @Column(name = "remedio_name", nullable = false)
    private String remedioName;

    @Column(name = "remedio_desc")
    private String remedioDesc;
}
