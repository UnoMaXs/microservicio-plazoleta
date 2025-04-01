package com.plazoleta.infrastructure.input.rest.client;

import com.plazoleta.application.dto.NotificacionesRequestDto;
import com.plazoleta.infrastructure.configuration.feing.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-mensajeria", url = "http://localhost:8082/notificaciones", configuration = FeignClientConfig.class)
public interface IMensajeriaFeignClient {


    @PostMapping("/pedido-listo")
    void enviarNotificacion(@RequestBody NotificacionesRequestDto notificacionesRequestDto);

}
