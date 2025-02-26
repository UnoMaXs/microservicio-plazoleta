package com.propietario.domain.spi;

import com.propietario.domain.model.Plato;

public interface IPlatoPersistencePort {
    void savePlato(Plato plato);
}
