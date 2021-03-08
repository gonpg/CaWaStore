package com.example.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Producto findByName(String nombre);

    @Query("SELECT p FROM Producto p WHERE lower(p.name) LIKE lower(concat('%', ?1,'%'))")
    List<Producto> findByNameIsLike(String name);

}

