package com.titorjs.control_vacario_api.repository;

import com.titorjs.control_vacario_api.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByProductoDesc(String productoDesc);
}