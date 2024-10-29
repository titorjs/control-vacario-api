package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "produccion_diario")
@Data
public class ProduccionDiaria {

    @EmbeddedId
    private ProduccionDiarioId id;

    @Column(name = "produccion_liters", nullable = false)
    private Double produccionLiters;

    @Embeddable
    @Data
    public static class ProduccionDiarioId implements java.io.Serializable {
        @ManyToOne
        @JoinColumn(name = "vaca_id", nullable = false)
        private Vaca vaca;

        @Column(name = "produccion_date", nullable = false)
        private LocalDate produccionDate;
    }
}