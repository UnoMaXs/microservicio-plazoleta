package com.plazoleta.infrastructure.output.jpa.mapper;


import com.plazoleta.domain.model.Restaurante;
import com.plazoleta.infrastructure.output.jpa.entity.RestauranteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteEntityMapper {

    RestauranteEntity toRestauranteEntity(Restaurante restaurante);
    Restaurante toRestaurante(RestauranteEntity restauranteEntity);
}
