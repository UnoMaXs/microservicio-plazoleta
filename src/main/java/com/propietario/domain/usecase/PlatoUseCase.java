package com.propietario.domain.usecase;

import com.propietario.domain.api.IPlatoServicePort;
import com.propietario.domain.model.Plato;
import com.propietario.domain.spi.IPlatoPersistencePort;

public class PlatoUseCase implements IPlatoServicePort {

    private final IPlatoPersistencePort platoPersistencePort;

    public PlatoUseCase(IPlatoPersistencePort platoPersistencePort) {
        this.platoPersistencePort = platoPersistencePort;
    }


    @Override
    public void savePlato(Plato plato) {

        platoPersistencePort.savePlato(plato);
    }
}
