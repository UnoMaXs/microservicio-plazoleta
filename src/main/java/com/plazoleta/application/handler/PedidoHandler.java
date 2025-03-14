package com.plazoleta.application.handler;

import com.plazoleta.application.dto.PedidoRequestDto;
import com.plazoleta.application.dto.PedidoResponseDto;
import com.plazoleta.application.mapper.IPedidoAppRequestMapper;
import com.plazoleta.application.mapper.IPedidoAppResponseMapper;
import com.plazoleta.domain.api.IPedidoServicePort;
import com.plazoleta.domain.model.Pedido;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoHandler implements IPedidoAppHandler{

    private final IPedidoServicePort pedidoServicePort;
    private final IPedidoAppRequestMapper pedidoRequestMapper;
    private final IPedidoAppResponseMapper pedidoResponseMapper;

    @Override
    public PedidoResponseDto savePedido(PedidoRequestDto requestDto) {
        Pedido pedido = pedidoRequestMapper.toPedido(requestDto);

        Pedido pedidoCreado = pedidoServicePort.savePedido(pedido);

        return pedidoResponseMapper.toPedidoResponseDto(pedidoCreado);
    }
}
