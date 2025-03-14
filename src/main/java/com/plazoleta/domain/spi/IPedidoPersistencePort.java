package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Pedido;

public interface IPedidoPersistencePort {

    Pedido savePedido(Pedido pedido);
    boolean usuarioTienePedidoActivo(Long idUsuario);

}
