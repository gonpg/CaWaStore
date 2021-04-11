package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    	 Optional<Usuario> user = usuarioRepository.findByNombreUsuario(auth.getName());
          	 

    	 if (!user.isPresent()) {
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        String contrasenya = (String) auth.getCredentials();
        if (!new BCryptPasswordEncoder().matches(contrasenya, user.get().getContrasenya())) {
            throw new BadCredentialsException("Contraseña incorrecta");
        }
 


        List<GrantedAuthority> roles = new ArrayList<>();
        for (String role : user.get().getRoles()) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return new UsernamePasswordAuthenticationToken(user.get().getNombreUsuario(), contrasenya, roles);
    }

    @Override
    public boolean supports(Class<?> authenticationObject) {
        return true;
    }
} 