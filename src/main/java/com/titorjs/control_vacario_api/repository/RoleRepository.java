package com.titorjs.control_vacario_api.repository;

import com.titorjs.control_vacario_api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}