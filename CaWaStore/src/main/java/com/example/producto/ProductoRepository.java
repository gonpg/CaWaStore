package com.example.producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "productos")
public interface ProductoRepository extends JpaRepository<Producto, Long> {
   
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@CacheEvict(allEntries = true)
	Producto save(Producto producto);

	@Transactional(readOnly = true)
    @Cacheable
    List<Producto> findAll();

	@Transactional(readOnly = true)
    @Cacheable
    Optional<Producto> findById(Long id);

	@Transactional(readOnly = true)
	@Cacheable
	Producto findByNombre(String nombre);
	
	@CacheEvict(allEntries = true)
    void deleteById(Long id);

	@Transactional(readOnly = true)
    @Query("SELECT p FROM Producto p WHERE lower(p.nombre) LIKE lower(concat('%', ?1,'%'))")
    List<Producto> findByNombreIsLike(String productName);

}

