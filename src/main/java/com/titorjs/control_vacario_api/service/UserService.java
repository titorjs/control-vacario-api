package com.titorjs.control_vacario_api.service;

import com.titorjs.control_vacario_api.entity.Role;
import com.titorjs.control_vacario_api.entity.User;
import com.titorjs.control_vacario_api.repository.RoleRepository;
import com.titorjs.control_vacario_api.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(String username, String password, String roleName, String name, String lastname, LocalDate birth) {
        // Verificar si el usuario ya existe
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("El usuario ya existe");
        }

        Role role = roleRepository.findByName(roleName).orElseThrow(() -> new RuntimeException("Rol no existe"));

        // Crear nuevo usuario
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setLastname(lastname);
        user.setBirth(birth);
        user.setRole(role);

        // Guardar el usuario
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    public void updateRole(Long userId, String roleName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        if (!user.getRole().equals(role)) {
            user.setRole(role);
            userRepository.save(user);  // Guardar los cambios en la base de datos
        } else {
            throw new RuntimeException("El usuario ya tiene este rol");
        }
    }
}
