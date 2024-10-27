package com.titorjs.control_vacario_api.repository;

import com.titorjs.control_vacario_api.entity.VacaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VacaStatusRepository extends JpaRepository<VacaStatus, Long> {
    Optional<VacaStatus> findByDescription(String desctiption);
}
