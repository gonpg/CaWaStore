package com.example.resena;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface ResenaRepository extends JpaRepository<Resena, Long> {

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@CacheEvict(value = "productos", allEntries = true)
    Resena save(Resena resena);
}