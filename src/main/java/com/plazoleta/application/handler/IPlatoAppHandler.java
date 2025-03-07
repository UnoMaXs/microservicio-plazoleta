package com.plazoleta.application.handler;

import com.plazoleta.application.dto.PlatoAppRequestDto;
import com.plazoleta.application.dto.PlatoUpdateRequestDto;
import com.plazoleta.application.dto.PlatoUpdateResponseDto;

public interface IPlatoAppHandler {

    void savePlatoInPlatoApp(PlatoAppRequestDto platoAppRequestDto);
    PlatoUpdateResponseDto updatePlatoInPlatoApp(Long platoId,PlatoUpdateRequestDto platoUpdateRequestDto, Long usuarioId);
}
