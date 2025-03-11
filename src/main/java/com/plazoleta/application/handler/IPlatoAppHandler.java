package com.plazoleta.application.handler;

import com.plazoleta.application.dto.*;

public interface IPlatoAppHandler {

    void savePlatoInPlatoApp(PlatoAppRequestDto platoAppRequestDto);
    PlatoUpdateResponseDto updatePlatoInPlatoApp(Long platoId,PlatoUpdateRequestDto platoUpdateRequestDto, Long usuarioId);
    PlatoUpdateEstadoResponseDto updateEstadoPlatoInPlatoApp(Long platoId, Boolean nuevoEstado, Long usuarioId);

}
