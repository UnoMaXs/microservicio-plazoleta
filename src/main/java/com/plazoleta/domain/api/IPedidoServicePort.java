package com.plazoleta.domain.api;

import com.plazoleta.domain.model.EstadoPedido;
import com.plazoleta.domain.model.Pedido;
import org.springframework.data.domain.Page;

public interface IPedidoServicePort {

    Pedido savePedido(Pedido pedido);
    Page<Pedido> getPedidosPorEstados(Long restauranteId, EstadoPedido estado, int page, int size);
    Pedido asignarEmpleadoYPonerEnPreparacion(Long idPedido, Long idEmpleado);
    Pedido marcarPedidoComoListo(Long idPedido);
    Pedido entregarPedido(Long idPedido, String pinIngresado);
    Pedido cambiarEstadoPedido(Long idPedido, EstadoPedido nuevoEstado);
}
