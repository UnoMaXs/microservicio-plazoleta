package com.plazoleta.application.mapper;

import com.plazoleta.application.dto.PlatoAppRequestDto;
import com.plazoleta.application.dto.PlatoUpdateEstadoRequestDto;
import com.plazoleta.application.dto.PlatoUpdateRequestDto;
import com.plazoleta.domain.model.Plato;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatoAppRequestMapper {

    Plato toPlato(PlatoAppRequestDto platoAppRequestDto);
    Plato toPlato(PlatoUpdateRequestDto platoUpdateRequestDto);
    String toEstadoString(PlatoUpdateEstadoRequestDto platoUpdateEstadoRequestDto);

}
