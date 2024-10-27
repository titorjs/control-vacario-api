package com.titorjs.control_vacario_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "user_username", unique = true, nullable = false)
    private String username;
    @Column(name = "user_password", nullable = false)
    private String password;
    @Column(name = "user_name", nullable = false)
    private String name;
    @Column(name = "user_lastname", nullable = false)
    private String lastname;
    @Column(name = "user_birth", nullable = false)
    private LocalDate birth;

    @ManyToOne
    @JoinColumn(name = "role_id",
                referencedColumnName = "role_id")
    private Role role;

    public User() {}
}
