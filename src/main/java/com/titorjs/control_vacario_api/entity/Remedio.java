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

    @Column(name = "remedio_name")
    private String remedioName;

    @Column(name = "remedio_desc")
    private String remedioDesc;

    @ManyToMany
    @JoinTable(
            name = "enfermedad_remedio",
            joinColumns = @JoinColumn(name = "remedio_id"),
            inverseJoinColumns = @JoinColumn(name = "enfermedad_id")
    )
    private Set<Enfermedad> enfermedades;
}
