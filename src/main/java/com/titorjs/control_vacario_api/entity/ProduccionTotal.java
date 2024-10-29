package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "produccion_total")
@Data
public class ProduccionTotal {

    @EmbeddedId
    private ProduccionTotalId id;

    @Column(name = "produccion_real", nullable = false)
    private Double produccionReal;

    @Column(name = "produccion_calculada")
    private Double produccionCalculada;

    @Embeddable
    @Data
    public static class ProduccionTotalId implements java.io.Serializable {
        @ManyToOne
        @JoinColumn(name = "producto_id")
        private Producto producto;

        @Column(name = "produccion_date")
        private LocalDate produccionDate;
    }
}