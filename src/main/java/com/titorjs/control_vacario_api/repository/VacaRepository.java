package com.titorjs.control_vacario_api.repository;

import com.titorjs.control_vacario_api.entity.Vaca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacaRepository extends JpaRepository<Vaca, Long> {
    Vaca findByCode(String code);
}
