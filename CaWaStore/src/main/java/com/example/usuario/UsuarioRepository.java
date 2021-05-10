package com.example.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    Usuario save(Usuario usuario);

    @Transactional(readOnly = true)
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

}