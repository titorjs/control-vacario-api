package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "venta_queso")
@Data
public class VentaQueso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venta_queso_id")
    private Long ventaQuesoId;

    @Column(name = "venta_queso_init")
    private LocalDate ventaQuesoInit;

    @Column(name = "venta_queso_end")
    private LocalDate ventaQuesoEnd;

    @Column(name = "venta_queso_pounds")
    private Double ventaQuesoPounds;

    @Column(name = "venta_queso_left")
    private Double ventaQuesoLeft;
}
