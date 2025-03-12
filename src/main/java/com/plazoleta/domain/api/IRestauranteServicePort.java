package com.plazoleta.domain.api;


import com.plazoleta.domain.model.Restaurante;

import java.util.List;

public interface IRestauranteServicePort {

    void saveRestaurante(Restaurante restaurante);

    List<Restaurante> getAllRestaurantes(int page, int size);

}
