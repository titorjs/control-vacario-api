package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "venta_leche")
@Data
public class VentaLeche {

    @Id
    @Column(name = "venta_lechera_date")
    private LocalDate ventaLecheraDate;

    @Column(name = "venta_lechera_liters", nullable = false)
    private Double ventaLecheraLiters;

    @Column(name = "venta_lechera_ppl", nullable = false)
    private Double ventaLecheraPpl;
}
