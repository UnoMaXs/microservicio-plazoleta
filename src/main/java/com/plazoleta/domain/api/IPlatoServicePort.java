package com.plazoleta.domain.api;

import com.plazoleta.domain.model.Plato;

public interface IPlatoServicePort {
    void savePlato(Plato plato);
    Plato updatePlato(Long idPlato, Plato platoModificado, Long idUsuario);
    Plato updateEstadoPlato(Long idPlato, boolean nuevoEstado, Long idUsuario);

}
