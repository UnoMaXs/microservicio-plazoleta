package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IPlatoServicePort;
import com.plazoleta.domain.api.IUsuarioServicePort;
import com.plazoleta.domain.model.Plato;
import com.plazoleta.domain.spi.IPlatoPersistencePort;
import com.plazoleta.domain.spi.IRestaurantePersistencePort;
import com.plazoleta.infrastructure.exception.BusinessException;

public class PlatoUseCase implements IPlatoServicePort {

    private final IPlatoPersistencePort platoPersistencePort;
    private final IRestaurantePersistencePort restaurantePersistencePort;
    private final IUsuarioServicePort usuarioServicePort;

    public PlatoUseCase(IPlatoPersistencePort platoPersistencePort, IRestaurantePersistencePort restaurantePersistencePort, IUsuarioServicePort usuarioServicePort) {
        this.platoPersistencePort = platoPersistencePort;
        this.restaurantePersistencePort = restaurantePersistencePort;
        this.usuarioServicePort = usuarioServicePort;
    }


    @Override
    public void savePlato(Plato plato) {

        if (!"PROPIETARIO".equalsIgnoreCase(usuarioServicePort.obtenerRolUsuario(plato.getIdUsuario()))) {
            throw new BusinessException("El usuario no tiene permisos para crear platos.");
        }


        if (restaurantePersistencePort.findRestauranteById(plato.getIdRestaurante()) == null) {
            throw new BusinessException("El plato debe estar asociado a un restaurante válido.");
        }

        plato.setActivoPlato(true);

        platoPersistencePort.savePlato(plato);
    }
}
