package com.plazoleta.domain.spi;


import com.plazoleta.domain.model.Restaurante;

public interface IRestaurantePersistencePort {

     void saveRestaurante(Restaurante restaurante);
}
