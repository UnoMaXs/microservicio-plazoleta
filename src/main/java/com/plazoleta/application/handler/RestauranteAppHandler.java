package com.plazoleta.application.handler;

import com.plazoleta.application.dto.RestauranteAppRequestDto;
import com.plazoleta.domain.api.IRestauranteServicePort;
import com.plazoleta.domain.model.Restaurante;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Transactional
public class RestauranteAppHandler implements IRestauranteAppHandler {

    private final IRestauranteServicePort restauranteServicePort;


    @Override
    public void saveRestauranteInRestauranteApp(RestauranteAppRequestDto restauranteAppRequestDto) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNombreRestaurante(restauranteAppRequestDto.getNombreRestaurante());
        restaurante.setNit(restauranteAppRequestDto.getNit());
        restaurante.setDireccion(restauranteAppRequestDto.getDireccion());
        restaurante.setTelefonoRestaurante(restauranteAppRequestDto.getTelefonoRestaurante());
        restaurante.setUrlLogo(restauranteAppRequestDto.getUrlLogo());
        restaurante.setIdUsuario(restauranteAppRequestDto.getIdUsuario());
        restaurante.setIdRolPropietario(restauranteAppRequestDto.getIdRolPropietario());

        restauranteServicePort.saveRestaurante(restaurante);
    }
}
