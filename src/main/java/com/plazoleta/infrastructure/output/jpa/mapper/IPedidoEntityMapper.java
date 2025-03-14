package com.plazoleta.infrastructure.output.jpa.mapper;

import com.plazoleta.domain.model.Pedido;
import com.plazoleta.infrastructure.output.jpa.entity.PedidoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoEntityMapper {

    PedidoEntity toPedidoEntity(Pedido pedido);
    Pedido toPedido(PedidoEntity pedidoEntity);
}
