package com.plazoleta.infrastructure.output.jpa.adapter;

import com.plazoleta.domain.model.Restaurante;
import com.plazoleta.domain.spi.IRestaurantePersistencePort;
import com.plazoleta.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.plazoleta.infrastructure.output.jpa.repository.IRestauranteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauranteJpaAdapter implements IRestaurantePersistencePort {
    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;

    @Override
    public void saveRestaurante(Restaurante restaurante) {
        restauranteRepository.save(restauranteEntityMapper.toRestauranteEntity(restaurante));
    }
}
