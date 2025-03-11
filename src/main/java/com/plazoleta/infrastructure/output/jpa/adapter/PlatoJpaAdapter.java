package com.plazoleta.infrastructure.output.jpa.adapter;

import com.plazoleta.domain.model.Plato;
import com.plazoleta.domain.spi.IPlatoPersistencePort;
import com.plazoleta.infrastructure.exception.BusinessException;
import com.plazoleta.infrastructure.output.jpa.mapper.IPlatoEntityMapper;
import com.plazoleta.infrastructure.output.jpa.repository.IPlatoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlatoJpaAdapter implements IPlatoPersistencePort {
    private final IPlatoRepository platoRepository;
    private final IPlatoEntityMapper platoEntityMapper;

    @Override
    public void savePlato(Plato plato) {
        platoRepository.save(platoEntityMapper.toPlatoEntity(plato));
    }

    @Override
    public Plato findPlatoById(Long idPlato) {
        return platoRepository.findById(idPlato)
                .map(platoEntityMapper::toPlato)
                .orElseThrow(() -> new BusinessException("El plato no existe"));
    }

    @Override
    public Plato updateEstadoPlato(Long idPlato, boolean nuevoEstado, Long idUsuario) {
        var platoEntity = platoRepository.findById(idPlato)
                .orElseThrow(() -> new BusinessException("El plato no existe"));


        platoEntity.setEstado(nuevoEstado);


        platoRepository.save(platoEntity);


        return platoEntityMapper.toPlato(platoEntity);
    }
}
