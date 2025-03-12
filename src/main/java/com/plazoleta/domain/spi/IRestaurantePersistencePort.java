package com.plazoleta.domain.spi;


import com.plazoleta.domain.model.Restaurante;

import java.util.List;

public interface IRestaurantePersistencePort {

     void saveRestaurante(Restaurante restaurante);
     Restaurante findRestauranteById(Long id);
     List<Restaurante> findAllRestaurantsOrderedByName(int page, int size);

}
