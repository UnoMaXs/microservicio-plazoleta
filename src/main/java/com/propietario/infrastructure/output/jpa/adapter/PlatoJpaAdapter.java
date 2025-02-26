package com.propietario.infrastructure.output.jpa.adapter;

import com.propietario.domain.model.Plato;
import com.propietario.domain.spi.IPlatoPersistencePort;
import com.propietario.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.propietario.infrastructure.output.jpa.repository.IPlatoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlatoJpaAdapter implements IPlatoPersistencePort {
    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;

    @Override
    public void savePlato(Plato plato) {
        platoRepository.save(platoEntityMapper.toPlatoEntity(plato));
    }
}
