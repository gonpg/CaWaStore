package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.usuario.Usuario;
import com.example.usuario.UsuarioRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioRepositoryAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        Usuario user = usuarioRepository.findByNombreUsuario(auth.getName());

        if (user == null) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        String contrasenya = (String) auth.getCredentials();
        System.out.println(user.getContrasenya());
        System.out.println(contrasenya);
        if (!new BCryptPasswordEncoder().matches(contrasenya, user.getContrasenya())) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }
 


        List<GrantedAuthority> roles = new ArrayList<>();
        for (String rol : user.getRol()) {
            roles.add(new SimpleGrantedAuthority("ROL_" + rol));
        }

        return new UsernamePasswordAuthenticationToken(user.getNombreUsuario(), contrasenya, roles);
    }

    @Override
    public boolean supports(Class<?> authenticationObject) {
        return true;
    }
} 