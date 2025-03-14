package com.plazoleta.application.mapper;

import com.plazoleta.application.dto.PedidoItemRequestDto;
import com.plazoleta.application.dto.PedidoRequestDto;
import com.plazoleta.domain.model.Pedido;
import com.plazoleta.domain.model.PedidoItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoAppRequestMapper {

    Pedido toPedido(PedidoRequestDto pedidoRequestDto);

    PedidoItem toPedidoItem(PedidoItemRequestDto pedidoItemRequestDto);
}
