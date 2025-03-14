package com.plazoleta.infrastructure.output.jpa.repository;

import com.plazoleta.infrastructure.output.jpa.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestauranteRepository extends JpaRepository<RestauranteEntity, Long> {
}
