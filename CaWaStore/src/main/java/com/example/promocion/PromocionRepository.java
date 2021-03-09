package com.example.promocion;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PromocionRepository extends JpaRepository<Promocion, Long> {

    Promocion findByProductoId(Long id);

}

