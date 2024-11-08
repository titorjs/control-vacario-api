package com.titorjs.control_vacario_api.repository;

import com.titorjs.control_vacario_api.entity.Role;
import com.titorjs.control_vacario_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
