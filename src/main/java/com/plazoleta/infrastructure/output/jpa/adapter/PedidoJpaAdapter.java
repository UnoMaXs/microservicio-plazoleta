package com.plazoleta.infrastructure.output.jpa.adapter;

import com.plazoleta.domain.model.EstadoPedido;
import com.plazoleta.domain.model.Pedido;
import com.plazoleta.domain.spi.IPedidoPersistencePort;
import com.plazoleta.infrastructure.output.jpa.entity.PedidoEntity;
import com.plazoleta.infrastructure.output.jpa.entity.PedidoItemEntity;
import com.plazoleta.infrastructure.output.jpa.mapper.IPedidoEntityMapper;
import com.plazoleta.infrastructure.output.jpa.repository.IPedidoRepository;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PedidoJpaAdapter implements IPedidoPersistencePort {

    private final IPedidoRepository pedidoRepository;
    private final IPedidoEntityMapper pedidoEntityMapper;

    @Override
    public Pedido savePedido(Pedido pedido) {
        PedidoEntity pedidoEntity = pedidoEntityMapper.toPedidoEntity(pedido);

        if (pedidoEntity.getItems() != null) {
            for (PedidoItemEntity itemEntity : pedidoEntity.getItems()) {
                itemEntity.setPedido(pedidoEntity);
            }
        }

        PedidoEntity savedPedidoEntity = pedidoRepository.save(pedidoEntity);
        return pedidoEntityMapper.toPedido(savedPedidoEntity);
    }

    @Override
    public boolean usuarioTienePedidoActivo(Long idUsuario) {

        return pedidoRepository.existsByIdClienteAndEstadoIn(
                idUsuario,
                List.of(EstadoPedido.PENDIENTE, EstadoPedido.EN_PREPARACION, EstadoPedido.LISTO));
    }
}
