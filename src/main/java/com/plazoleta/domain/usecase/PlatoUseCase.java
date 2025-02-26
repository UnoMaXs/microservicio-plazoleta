package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IPlatoServicePort;
import com.plazoleta.domain.model.Plato;
import com.plazoleta.domain.spi.IPlatoPersistencePort;

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
