package com.example.promocion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


public interface PromocionRepository extends JpaRepository<Promocion, Long> {

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    Promocion save(Promocion promotion);
	
	@Transactional(readOnly = true)
    Promocion findByProductoId(Long id);

}

