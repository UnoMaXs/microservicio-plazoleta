package com.plazoleta.application.handler;

import com.plazoleta.application.dto.RestauranteAppRequestDto;
import com.plazoleta.application.dto.RestauranteAppResponseDto;

import java.util.List;

public interface IRestauranteAppHandler {

    void saveRestauranteInRestauranteApp(RestauranteAppRequestDto restauranteAppRequestDto);
    List<RestauranteAppResponseDto> listRestaurantes(int page, int size);
}
