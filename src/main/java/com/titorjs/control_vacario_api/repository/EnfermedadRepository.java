package com.titorjs.control_vacario_api.repository;

import com.titorjs.control_vacario_api.entity.Enfermedad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnfermedadRepository extends JpaRepository<Enfermedad, Long> {
    @Query("SELECT COUNT(e) FROM Enfermedad e JOIN e.remedios r WHERE r.remedioId = :remedioId")
    long countEnfermedadesByRemedioId(@Param("remedioId") Long remedioId);
}
