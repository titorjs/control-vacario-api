package com.titorjs.control_vacario_api.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.titorjs.control_vacario_api.util.JwtTokenUtil;

@RestController
@RequestMapping("/api/${api.version}/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // Obtenemos el UserDetails desde el objeto Authentication
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Si la autenticación es exitosa, generamos el token
            final String jwt = jwtTokenUtil.generateToken(userDetails);
            return jwt;
        } catch (AuthenticationException e) {
            throw new Exception("Credenciales inválidas", e);
        }
    }
}

@Data
class AuthRequest {
    private String username;
    private String password;
}
