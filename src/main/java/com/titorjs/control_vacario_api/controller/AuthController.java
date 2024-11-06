package com.titorjs.control_vacario_api.controller;

import com.titorjs.control_vacario_api.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.titorjs.control_vacario_api.util.JwtTokenUtil;
import com.titorjs.control_vacario_api.entity.User;

import java.util.Map;

@RestController
@RequestMapping("/api/${api.version}/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            if (authRequest.getUsername() == null || authRequest.getPassword() == null
                    || authRequest.getUsername().isEmpty() || authRequest.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("Información incompleta");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // Obtenemos el UserDetails desde el objeto Authentication
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generamos el token JWT
            final String jwt = jwtTokenUtil.generateToken(userDetails);

            // Obtenemos los detalles adicionales del usuario
            User user = userService.getUserByUsername(authRequest.getUsername());

            // Devolvemos el token y los detalles del usuario en un objeto JSON
            return ResponseEntity.ok().body(Map.of(
                    "token", jwt,
                    "id", user.getId(),
                    "name", user.getName(),
                    "lastname", user.getLastname()
            ));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Credenciales incorrectas");
        }
    }

}

@Data
class AuthRequest {
    private String username;
    private String password;
}
