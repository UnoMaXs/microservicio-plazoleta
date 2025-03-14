package com.plazoleta.infrastructure.output.jpa.mapper;

import com.plazoleta.domain.model.PedidoItem;
import com.plazoleta.infrastructure.output.jpa.entity.PedidoItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoItemEntityMapper {

    PedidoItemEntity toPedidoItemEntity(PedidoItem pedidoItem);
    PedidoItem toPedidoItem(PedidoItemEntity pedidoItemEntity);
}
