package com.plazoleta.application.handler;

import com.plazoleta.application.dto.PedidoRequestDto;
import com.plazoleta.application.dto.PedidoResponseDto;

public interface IPedidoAppHandler {

    PedidoResponseDto savePedido(PedidoRequestDto requestDto);

}
