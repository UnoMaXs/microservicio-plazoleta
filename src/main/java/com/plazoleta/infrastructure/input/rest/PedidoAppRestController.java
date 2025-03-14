package com.plazoleta.infrastructure.input.rest;

import com.plazoleta.application.dto.PedidoRequestDto;
import com.plazoleta.application.dto.PedidoResponseDto;
import com.plazoleta.application.handler.IPedidoAppHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidoApp")
@RequiredArgsConstructor
public class PedidoAppRestController {

    private final IPedidoAppHandler pedidoAppHandler;

    @PostMapping("/savePedido")
    public ResponseEntity<PedidoResponseDto> savePedido(@RequestBody PedidoRequestDto pedidoRequestDto) {
        PedidoResponseDto pedidoResponseDto = pedidoAppHandler.savePedido(pedidoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponseDto);
    }
}
