package com.plazoleta.domain.api;

import com.plazoleta.domain.model.Pedido;

public interface IPedidoServicePort {

    Pedido savePedido(Pedido pedido);
}
