package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vacas")
@Data
public class Vaca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vaca_id")
    private Long id;

    @Column(name = "vaca_code", unique = true, nullable = false)
    private String code;

    @Column(name = "vaca_name", nullable = false)
    private String name;

    @Column(name = "vaca_desc")
    private String description;

    // Relación con VacaType
    @ManyToOne
    @JoinColumn(name = "vaca_type_id", referencedColumnName = "vaca_type_id")
    private VacaType type;

    // Relación con VacaStatus
    @ManyToOne
    @JoinColumn(name = "vaca_status_id", referencedColumnName = "vaca_status_id")
    private VacaStatus status;

    // Constructor vacío
    public Vaca() {}

    // Constructor con todos los campos menos el ID
    public Vaca(String code, String name, String description, VacaType type, VacaStatus status) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.type = type;
        this.status = status;
    }
}
