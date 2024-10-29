package com.titorjs.control_vacario_api.repository;

import com.titorjs.control_vacario_api.entity.Remedio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemedioRepository extends JpaRepository<Remedio, Long> {
}
