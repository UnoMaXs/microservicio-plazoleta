package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IUsuarioServicePort;

public class UsuarioUseCase {

    private final IUsuarioServicePort usuarioServicePort;

    public UsuarioUseCase(IUsuarioServicePort usuarioServicePort) {
        this.usuarioServicePort = usuarioServicePort;
    }


    public String validarRolUsuario(Long id){
        return usuarioServicePort.obtenerRolUsuario(id);
    }
}
