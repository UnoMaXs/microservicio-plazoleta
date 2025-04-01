package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.EstadoPedido;
import com.plazoleta.domain.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IPedidoPersistencePort {

    Pedido savePedido(Pedido pedido);
    boolean usuarioTienePedidoActivo(Long idUsuario);
    Page<Pedido> findPedidosPorEstadoYRestaurante(EstadoPedido estado, Long restauranteId, PageRequest pageRequest);
    Pedido findById(Long idPedido);


}
