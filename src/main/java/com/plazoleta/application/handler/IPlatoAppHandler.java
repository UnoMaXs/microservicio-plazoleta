package com.plazoleta.application.handler;

import com.plazoleta.application.dto.*;

import java.util.List;

public interface IPlatoAppHandler {

    void savePlatoInPlatoApp(PlatoAppRequestDto platoAppRequestDto);

    PlatoUpdateResponseDto updatePlatoInPlatoApp(Long platoId, PlatoUpdateRequestDto platoUpdateRequestDto, Long usuarioId);

    PlatoUpdateEstadoResponseDto updateEstadoPlatoInPlatoApp(Long platoId, Boolean nuevoEstado, Long usuarioId);

    List<PlatoAppResponseDto> listPlatosMenu(Long idRestaurante, String categoria, int page, int size);
}


