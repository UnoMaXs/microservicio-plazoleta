package com.plazoleta.application.handler;

import com.plazoleta.application.dto.PedidoRequestDto;
import com.plazoleta.application.dto.PedidoResponseDto;
import com.plazoleta.application.mapper.IPedidoAppRequestMapper;
import com.plazoleta.application.mapper.IPedidoAppResponseMapper;
import com.plazoleta.domain.api.IPedidoServicePort;
import com.plazoleta.domain.model.EstadoPedido;
import com.plazoleta.domain.model.Pedido;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @Override
    public Page<Pedido> getPedidosPorEstado(Long restauranteId, EstadoPedido estado, int page, int size) {
        return pedidoServicePort.getPedidosPorEstados(restauranteId, estado, page, size);
    }

    @Override
    public PedidoResponseDto asignarEmpleadoYPonerEnPreparacion(Long idPedido, Long idEmpleado) {
        Pedido pedido = pedidoServicePort.asignarEmpleadoYPonerEnPreparacion(idPedido, idEmpleado);
        return pedidoResponseMapper.toPedidoResponseDto(pedido);
    }

    @Override
    public PedidoResponseDto marcarPedidoComoListo(Long idPedido) {
        Pedido pedido = pedidoServicePort.marcarPedidoComoListo(idPedido);
        return pedidoResponseMapper.toPedidoResponseDto(pedido);
    }

    @Override
    public PedidoResponseDto entregarPedido(Long idPedido, String pinIngresado) {
        Pedido pedidoEntregado = pedidoServicePort.entregarPedido(idPedido, pinIngresado);

        return pedidoResponseMapper.toPedidoResponseDto(pedidoEntregado);
    }


    @Override
    public PedidoResponseDto cambiarEstadoPedido(Long idPedido, EstadoPedido nuevoEstado) {
        Pedido pedido = pedidoServicePort.cambiarEstadoPedido(idPedido, nuevoEstado);
        return pedidoResponseMapper.toPedidoResponseDto(pedido);

    }

}
