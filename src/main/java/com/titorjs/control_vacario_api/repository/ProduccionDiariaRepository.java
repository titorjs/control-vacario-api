package com.titorjs.control_vacario_api.repository;

import com.titorjs.control_vacario_api.entity.ProduccionDiaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ProduccionDiariaRepository extends JpaRepository<ProduccionDiaria, Long> {
    Optional<ProduccionDiaria> findByIdProduccionDateAndIdVacaId(LocalDate produccionDate, Long vacaId);
}
