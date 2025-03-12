package com.plazoleta.infrastructure.output.jpa.adapter;


import com.plazoleta.domain.model.Restaurante;
import com.plazoleta.domain.spi.IRestaurantePersistencePort;
import com.plazoleta.infrastructure.exception.BusinessException;
import com.plazoleta.infrastructure.output.jpa.entity.RestauranteEntity;
import com.plazoleta.infrastructure.output.jpa.mapper.IRestauranteEntityMapper;
import com.plazoleta.infrastructure.output.jpa.repository.IRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RestauranteJpaAdapter implements IRestaurantePersistencePort {

    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;

    @Override
    public void saveRestaurante(Restaurante restaurante) {
        restauranteRepository.save(restauranteEntityMapper.toRestauranteEntity(restaurante));
    }

    @Override
    public Restaurante findRestauranteById(Long id) {
        return restauranteRepository.findById(id)
                .map(restauranteEntityMapper::toRestaurante)
                .orElseThrow(() -> new BusinessException("El restaurante no existe"));
    }

    @Override
    public List<Restaurante> findAllRestaurantsOrderedByName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nombreRestaurante").ascending());
        Page<RestauranteEntity> pageResult = restauranteRepository.findAll(pageable);

        return pageResult.getContent()
                .stream()
                .map(restauranteEntityMapper::toRestaurante)
                .collect(Collectors.toList());
    }



}
