package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IRestauranteServicePort;
import com.plazoleta.domain.api.IUsuarioServicePort;
import com.plazoleta.domain.model.Restaurante;
import com.plazoleta.domain.spi.IRestaurantePersistencePort;
import com.plazoleta.infrastructure.exception.BusinessException;

import java.util.List;

public class RestauranteUseCase implements IRestauranteServicePort {

    private final IRestaurantePersistencePort restaurantePersistencePort;
    private final IUsuarioServicePort usuarioServicePort;

    public RestauranteUseCase(IRestaurantePersistencePort restaurantePersistencePort, IUsuarioServicePort usuarioServicePort) {
        this.restaurantePersistencePort = restaurantePersistencePort;
        this.usuarioServicePort = usuarioServicePort;
    }

    @Override
    public void saveRestaurante(Restaurante restaurante) {
        validarRolUsuario(restaurante.getIdUsuario(), "ADMINISTRADOR");
        validarRolUsuario(restaurante.getIdRolPropietario(), "PROPIETARIO");

        restaurante.setIdUsuario(restaurante.getIdRolPropietario());

        validarNit(restaurante.getNit());
        validarTelefono(restaurante.getTelefonoRestaurante());
        validarNombreRestaurante(restaurante.getNombreRestaurante());

        restaurantePersistencePort.saveRestaurante(restaurante);
    }

    @Override
    public List<Restaurante> getAllRestaurantes(int page, int size) {
        return restaurantePersistencePort.findAllRestaurantsOrderedByName(page, size);
    }

    @Override
    public Long getRestauranteById(Long id) {
        return restaurantePersistencePort.getRestauranteById(id);
    }

    private void validarRolUsuario(Long idUsuario, String rolEsperado) {
        String rolUsuario = usuarioServicePort.obtenerRolUsuario(idUsuario);
        if (!rolEsperado.equalsIgnoreCase(rolUsuario)) {
            throw new BusinessException("El usuario no tiene rol de " + rolEsperado + ".");
        }
    }

    private void validarNit(Long nit) {
        if (nit == null || nit <= 0) {
            throw new BusinessException("Documento de identidad debe ser un número positivo.");
        }
    }

    private void validarTelefono(String telefono) {
        if (telefono == null || !telefono.matches("\\+57\\d{8,13}")) {
            throw new BusinessException("Teléfono inválido; máximo 13 dígitos y debe iniciar con '+57'.");
        }
    }

    private void validarNombreRestaurante(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new BusinessException("El nombre del restaurante no puede estar vacío.");
        }
        if (nombre.matches("\\d+")) {
            throw new BusinessException("El nombre del restaurante no puede ser solo números.");
        }
    }
}
