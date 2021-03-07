package es.gonja.promocion;

import org.springframework.data.jpa.repository.JpaRepository;



public interface PromocionRepository extends JpaRepository<Promocion, Long> {

    Promocion findByProductId(Long id);

}

