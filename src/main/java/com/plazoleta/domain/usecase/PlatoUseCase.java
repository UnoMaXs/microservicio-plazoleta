package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IPlatoServicePort;
import com.plazoleta.domain.api.IUsuarioServicePort;
import com.plazoleta.domain.model.Plato;
import com.plazoleta.domain.model.Restaurante;
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
        String rol = usuarioServicePort.obtenerRolUsuario(plato.getIdUsuario());
        if (!"PROPIETARIO".equalsIgnoreCase(rol)) {
            throw new BusinessException("El usuario no tiene permisos para crear platos.");
        }


        Restaurante restaurante = restaurantePersistencePort.findRestauranteById(plato.getIdRestaurante());
        if (restaurante == null) {
            throw new BusinessException("El plato debe estar asociado a un restaurante válido.");
        }

        if (!restaurante.getIdUsuario().equals(plato.getIdUsuario())) {
            throw new BusinessException("No eres propietario de este restaurante, no puedes crear platos aquí.");
        }

        plato.setEstado(true);

        platoPersistencePort.savePlato(plato);
    }

    @Override
    public Plato   updatePlato(Long idPlato, Plato platoModificado, Long idUsuario) {

        String rol = usuarioServicePort.obtenerRolUsuario(idUsuario);
        if (!"PROPIETARIO".equalsIgnoreCase(rol)) {
            throw new BusinessException("El usuario no tiene permisos para modificar platos.");
        }

        Plato platoUpdate = platoPersistencePort.findPlatoById(idPlato);
        if (platoUpdate == null) {
            throw new BusinessException("El plato que intentas actualizar no existe.");
        }

        Restaurante restaurante = restaurantePersistencePort.findRestauranteById(platoUpdate.getIdRestaurante());
        if (restaurante == null) {
            throw new BusinessException("El restaurante asociado al plato no existe.");
        }

        if (!restaurante.getIdUsuario().equals(idUsuario)) {
            throw new BusinessException("No eres propietario de este restaurante, no puedes modificar este plato.");
        }


        platoUpdate.setDescripcionPlato(platoModificado.getDescripcionPlato());
        platoUpdate.setPrecioPlato(platoModificado.getPrecioPlato());
        platoPersistencePort.savePlato(platoUpdate);
        return platoUpdate;
    }

    @Override
    public Plato updateEstadoPlato(Long idPlato, boolean nuevoEstado, Long idUsuario) {

        String rol = usuarioServicePort.obtenerRolUsuario(idUsuario);
        if (!"PROPIETARIO".equalsIgnoreCase(rol)) {
            throw new BusinessException("El usuario no tiene permisos para habilitar o deshabilitar platos.");
        }


        Plato plato = platoPersistencePort.findPlatoById(idPlato);
        if (plato == null) {
            throw new BusinessException("El plato que intentas actualizar no existe.");
        }


        Restaurante restaurante = restaurantePersistencePort.findRestauranteById(plato.getIdRestaurante());
        if (restaurante == null) {
            throw new BusinessException("El restaurante asociado al plato no existe.");
        }


        if (!restaurante.getIdUsuario().equals(idUsuario)) {
            throw new BusinessException("No eres propietario de este restaurante, no puedes cambiar el estado del plato.");
        }


        plato.setEstado(nuevoEstado);
        platoPersistencePort.savePlato(plato);

        return plato;
    }


}
