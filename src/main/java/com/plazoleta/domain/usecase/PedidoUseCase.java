package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IPedidoServicePort;
import com.plazoleta.domain.api.IUsuarioServicePort;
import com.plazoleta.domain.model.EstadoPedido;
import com.plazoleta.domain.model.Pedido;
import com.plazoleta.domain.model.PedidoItem;
import com.plazoleta.domain.spi.IPedidoPersistencePort;
import com.plazoleta.domain.spi.IRestaurantePersistencePort;
import com.plazoleta.infrastructure.exception.BusinessException;

public class PedidoUseCase implements IPedidoServicePort {

    private final IPedidoPersistencePort pedidoPersistencePort;
    private final IUsuarioServicePort usuarioServicePort;
    private final IRestaurantePersistencePort restaurantePersistencePort;

    public PedidoUseCase(IPedidoPersistencePort pedidoPersistencePort, IUsuarioServicePort usuarioServicePort, IRestaurantePersistencePort restaurantePersistencePort) {
        this.pedidoPersistencePort = pedidoPersistencePort;
        this.usuarioServicePort = usuarioServicePort;
        this.restaurantePersistencePort = restaurantePersistencePort;
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        if (pedidoPersistencePort.usuarioTienePedidoActivo(
                pedido.getIdCliente())) {
            throw new BusinessException("El usuario ya tiene un pedido en proceso.");
        }

        pedido.setEstado(EstadoPedido.PENDIENTE);


        Pedido pedidoCreado = pedidoPersistencePort.savePedido(pedido);

        return pedidoCreado;
    }

    private void validarPedido(Pedido pedido) {
        if (pedido.getItems() == null || pedido.getItems().isEmpty()) {
            throw new BusinessException("El pedido no tiene platos.");
        }

        Long restauranteId = pedido.getIdRestaurante();
        for (PedidoItem item : pedido.getItems()) {
            if (!item.getIdRestaurante().equals(restauranteId)) {
                throw new BusinessException("Todos los platos deben ser del mismo restaurante.");
            }
            if (item.getCantidad() <= 0) {
                throw new BusinessException("La cantidad de cada plato debe ser mayor a cero.");
            }
        }
    }
}
