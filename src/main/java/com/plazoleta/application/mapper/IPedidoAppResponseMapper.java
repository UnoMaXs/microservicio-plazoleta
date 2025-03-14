package com.plazoleta.application.mapper;

import com.plazoleta.application.dto.PedidoItemResponseDto;
import com.plazoleta.application.dto.PedidoResponseDto;
import com.plazoleta.domain.model.Pedido;
import com.plazoleta.domain.model.PedidoItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidoAppResponseMapper {

PedidoResponseDto toPedidoResponseDto(Pedido pedido);

PedidoItemResponseDto toPedidoItemResponseDto(PedidoItem pedidoItem);
}
