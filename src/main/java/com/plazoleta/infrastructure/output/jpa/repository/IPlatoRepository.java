package com.plazoleta.infrastructure.output.jpa.repository;


import com.plazoleta.infrastructure.output.jpa.entity.PlatoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlatoRepository extends JpaRepository<PlatoEntity, Long> {

    Page<PlatoEntity> findAllByIdRestaurante(Long idRestaurante, Pageable pageable);
    Page<PlatoEntity> findAllByIdRestauranteAndCategoriaPlato(Long idRestaurante, String categoria, Pageable pageable);
}
