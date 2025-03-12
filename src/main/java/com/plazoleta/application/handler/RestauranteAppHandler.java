package com.plazoleta.application.handler;

import com.plazoleta.application.dto.RestauranteAppRequestDto;
import com.plazoleta.application.dto.RestauranteAppResponseDto;
import com.plazoleta.application.mapper.IRestauranteAppResponseMapper;
import com.plazoleta.domain.api.IRestauranteServicePort;
import com.plazoleta.domain.model.Restaurante;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class RestauranteAppHandler implements IRestauranteAppHandler {

    private final IRestauranteServicePort restauranteServicePort;
private final IRestauranteAppResponseMapper restauranteAppResponseMapper;

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

    @Override
    public List<RestauranteAppResponseDto> listRestaurantes(int page, int size) {
        List<Restaurante> restaurantes = restauranteServicePort.getAllRestaurantes(page, size);

        return restaurantes.stream()
                .map(restauranteAppResponseMapper::toRestauranteAppResponseDto)
                .collect(Collectors.toList());
    }

}
