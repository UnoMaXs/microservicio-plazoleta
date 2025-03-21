package com.plazoleta.application.mapper;

import com.plazoleta.application.dto.PlatoAppRequestDto;
import com.plazoleta.application.dto.PlatoAppResponseDto;
import com.plazoleta.application.dto.PlatoUpdateEstadoRequestDto;
import com.plazoleta.application.dto.PlatoUpdateResponseDto;
import com.plazoleta.domain.model.Plato;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoAppResponseMapper {

    PlatoAppResponseDto toPlatoAppResponseDto(Plato plato);
    PlatoUpdateResponseDto toPlatoUpdateResponseDto(Plato plato);
    PlatoUpdateEstadoRequestDto toPlatoUpdateEstadoRequestDto(Plato plato);

}
