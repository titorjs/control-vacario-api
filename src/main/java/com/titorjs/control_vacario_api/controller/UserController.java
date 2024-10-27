package com.titorjs.control_vacario_api.controller;

import com.titorjs.control_vacario_api.entity.User;
import com.titorjs.control_vacario_api.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/${api.version}/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            User user = userService
                    .registerNewUser(request.getUsername(), request.getPassword(),
                            request.getRole(), request.getName(),
                            request.getLastname(), request.getBirth());
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            if (user != null) {
                user.setPassword(null);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            if (user != null) {
                user.setPassword(null);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    // Quitar rol a un usuario
    @PutMapping("/{id}/updateRole")
    public ResponseEntity<?> removeRoleFromUser(@PathVariable Long id, @RequestParam String role) {
        try {
            userService.updateRole(id, role);
            return ResponseEntity.ok("Rol cambiado exitosamente");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

@Data
class UserRegistrationRequest {
    private String username;
    private String password;
    private String name;
    private String lastname;
    private LocalDate birth;
    private String role;
}
