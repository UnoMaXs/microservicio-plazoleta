package com.propietario.infrastructure.output.jpa.repository;


import com.propietario.infrastructure.output.jpa.entity.PlatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPlatoRepository extends JpaRepository<PlatoEntity, Long> {


}
