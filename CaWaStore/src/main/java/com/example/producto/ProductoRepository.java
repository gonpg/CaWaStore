package com.example.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "productos")
public interface ProductoRepository extends JpaRepository<Producto, Long> {
   
	@CacheEvict(allEntries = true)
	Producto save(Producto producto);

    @Cacheable
    List<Producto> findAll();

    @Cacheable
    Optional<Producto> findById(Long id);

    @Cacheable
	Producto findByNombre(String nombre);

    @Query("SELECT p FROM Producto p WHERE lower(p.nombre) LIKE lower(concat('%', ?1,'%'))")
    List<Producto> findByNombreIsLike(String productName);

}

