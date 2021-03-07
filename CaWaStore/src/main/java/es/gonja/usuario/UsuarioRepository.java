package es.gonja.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String nombreUsuario);

    Usuario findByName(String nombre);

}