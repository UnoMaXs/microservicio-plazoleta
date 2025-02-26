package com.propietario.application.mapper;

import com.propietario.application.dto.PlatoAppRequestDto;
import com.propietario.domain.model.Plato;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoAppResponseMapper {

    PlatoAppRequestDto toPlatoAppRequestDto(Plato plato);
}
