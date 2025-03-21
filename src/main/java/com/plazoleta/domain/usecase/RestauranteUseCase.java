package com.plazoleta.domain.usecase;


import com.plazoleta.domain.api.IRestauranteServicePort;
import com.plazoleta.domain.api.IUsuarioServicePort;
import com.plazoleta.domain.model.Restaurante;
import com.plazoleta.domain.spi.IRestaurantePersistencePort;
import com.plazoleta.infrastructure.exception.BusinessException;

import java.util.List;
import java.util.Optional;

public class RestauranteUseCase implements IRestauranteServicePort {

    private final IRestaurantePersistencePort restaurantePersistencePort;
    private final IUsuarioServicePort usuarioServicePort;

    public RestauranteUseCase(IRestaurantePersistencePort restaurantePersistencePort, IUsuarioServicePort usuarioServicePort) {
        this.restaurantePersistencePort = restaurantePersistencePort;
        this.usuarioServicePort = usuarioServicePort;
    }

    @Override
    public void saveRestaurante(Restaurante restaurante) {

        String rolAdmin = usuarioServicePort.obtenerRolUsuario(restaurante.getIdUsuario());
        if (!"ADMINISTRADOR".equalsIgnoreCase(rolAdmin)) {
            throw new BusinessException("El usuario no tiene rol de administrador.");
        }
        String rolPropietario = usuarioServicePort.obtenerRolUsuario(restaurante.getIdRolPropietario());
        if (!"PROPIETARIO".equalsIgnoreCase(rolPropietario)) {
            throw new BusinessException("Usuario propietario no tiene rol PROPIETARIO");
        }
        restaurante.setIdUsuario(restaurante.getIdRolPropietario());

        if (restaurante.getNit() == null || restaurante.getNit() <= 0) {
            throw new BusinessException("Documento de identidad debe ser un número positivo.");
        }
        if (!esTelefonoRestauranteValido(restaurante.getTelefonoRestaurante())) {
            throw new BusinessException("Teléfono inválido; máximo 13 dígitos y debe iniciar con '+57'.");
        }

        if (!esNombreRestauranteValido(restaurante.getNombreRestaurante())) {
            throw new BusinessException("El nombre de el restaurante no puede ser solo numeros. Ejemplo:'Mi Restaurante 21'");
        }



        restaurantePersistencePort.saveRestaurante(restaurante);
    }

    @Override
    public List<Restaurante> getAllRestaurantes(int page, int size) {
        return restaurantePersistencePort.findAllRestaurantsOrderedByName(page, size);
    }


    private boolean esTelefonoRestauranteValido(String telefonoRestaurante) {
        if (telefonoRestaurante == null) return false;

        return telefonoRestaurante.matches("\\+?\\d{1,13}");
    }

    private boolean esNombreRestauranteValido(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }

        if (nombre.matches("\\d+")) {
            return false;
        }
        return true;
    }

}
