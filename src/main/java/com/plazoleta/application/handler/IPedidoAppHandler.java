package com.plazoleta.application.handler;

import com.plazoleta.application.dto.PedidoRequestDto;
import com.plazoleta.application.dto.PedidoResponseDto;
import com.plazoleta.domain.model.EstadoPedido;
import com.plazoleta.domain.model.Pedido;
import org.springframework.data.domain.Page;

public interface IPedidoAppHandler {

    PedidoResponseDto savePedido(PedidoRequestDto requestDto);
    Page<Pedido> getPedidosPorEstado(Long restauranteId, EstadoPedido estado, int page, int size);


}
