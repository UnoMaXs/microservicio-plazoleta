package com.plazoleta.domain.spi;

import com.plazoleta.domain.model.Plato;

public interface IPlatoPersistencePort {
    void savePlato(Plato plato);
    Plato findPlatoById(Long idPlato);
}
