package com.plazoleta.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PedidoItemResponseDto {

    private Long idPlato;
    private int cantidad;
}
