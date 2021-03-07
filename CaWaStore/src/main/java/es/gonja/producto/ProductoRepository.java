package es.gonja.producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findByName(String nombre);

    @Query("SELECT p FROM Producto p WHERE lower(p.nombre) LIKE lower(concat('%', ?1,'%'))")
    List<Producto> findByNameIsLike(String name);

}

