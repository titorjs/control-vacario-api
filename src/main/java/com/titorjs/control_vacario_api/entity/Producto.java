package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_id")
    private Long productoId;

    @Column(name = "producto_desc")
    private String productoDesc;

    @Column(name = "producto_measur")
    private String productoMeasur;
}