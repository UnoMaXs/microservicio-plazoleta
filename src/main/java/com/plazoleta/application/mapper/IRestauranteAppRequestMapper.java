package com.plazoleta.application.mapper;


import com.plazoleta.application.dto.RestauranteAppRequestDto;
import com.plazoleta.domain.model.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteAppRequestMapper {
    Restaurante toRestaurante(RestauranteAppRequestDto restauranteAppRequestDto);
}
