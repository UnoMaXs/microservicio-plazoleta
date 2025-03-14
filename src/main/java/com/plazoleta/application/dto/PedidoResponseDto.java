package com.plazoleta.application.dto;

import com.plazoleta.domain.model.EstadoPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PedidoResponseDto {

    private Long idPedido;
    private EstadoPedido estado;
    private Long idCliente;
    private Long idRestaurante;
    private List<PedidoItemResponseDto> items;

}
