package com.plazoleta.infrastructure.output.jpa.adapter;

import com.plazoleta.domain.api.IUsuarioServicePort;
import com.plazoleta.infrastructure.input.rest.client.UsuarioFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioFeignAdapter implements IUsuarioServicePort {

    private final UsuarioFeignClient usuarioFeignClient;

    @Override
    public String obtenerRolUsuario(Long id) {
        return usuarioFeignClient.obtenerRol(id);
    }
}
