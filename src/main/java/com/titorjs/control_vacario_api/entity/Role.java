package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "role_desc", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return name;
    }
}
