package com.plazoleta.infrastructure.output.jpa.adapter;


import com.plazoleta.application.dto.NotificacionesRequestDto;
import com.plazoleta.domain.api.IMensajeriaServicePort;
import com.plazoleta.infrastructure.input.rest.client.IMensajeriaFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MensajeriaAdapter implements IMensajeriaServicePort {
    IMensajeriaFeignClient mensajeriaClient;

    @Override
    public void enviarNotificacion(String celular, String mensaje) {
        mensajeriaClient.enviarNotificacion(new NotificacionesRequestDto(celular, mensaje));
    }
}
