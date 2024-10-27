package com.titorjs.control_vacario_api.service;

import com.titorjs.control_vacario_api.entity.Role;
import com.titorjs.control_vacario_api.entity.User;
import com.titorjs.control_vacario_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con nombre: " + username));

        // Aquí el objeto `User` debe tener sus roles correctamente cargados
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRole()));  // Mapear los roles a GrantedAuthorities
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
        return new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(role.getName())));
    }
}

