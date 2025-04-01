package com.plazoleta.domain.usecase;

import com.plazoleta.domain.api.IMensajeriaServicePort;
import com.plazoleta.domain.api.IPedidoServicePort;
import com.plazoleta.domain.api.IUsuarioServicePort;
import com.plazoleta.domain.model.EstadoPedido;
import com.plazoleta.domain.model.Pedido;
import com.plazoleta.domain.model.PedidoItem;
import com.plazoleta.domain.spi.IPedidoPersistencePort;
import com.plazoleta.domain.spi.IRestaurantePersistencePort;
import com.plazoleta.infrastructure.exception.BusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public class PedidoUseCase implements IPedidoServicePort {

    private final IPedidoPersistencePort pedidoPersistencePort;
    private final IUsuarioServicePort usuarioServicePort;
    private final IRestaurantePersistencePort restaurantePersistencePort;
    private final IMensajeriaServicePort mensajeriaService;

    public PedidoUseCase(IPedidoPersistencePort pedidoPersistencePort,
                         IUsuarioServicePort usuarioServicePort,
                         IRestaurantePersistencePort restaurantePersistencePort, IMensajeriaServicePort mensajeriaService) {
        this.pedidoPersistencePort = pedidoPersistencePort;
        this.usuarioServicePort = usuarioServicePort;
        this.restaurantePersistencePort = restaurantePersistencePort;
        this.mensajeriaService = mensajeriaService;
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        validarPedido(pedido);

        if (pedidoPersistencePort.usuarioTienePedidoActivo(pedido.getIdCliente())) {
            throw new BusinessException("El usuario ya tiene un pedido en proceso.");
        }

        pedido.setEstado(EstadoPedido.PENDIENTE);
        return pedidoPersistencePort.savePedido(pedido);
    }

    @Override
    public Page<Pedido> getPedidosPorEstados(Long restauranteId, EstadoPedido estado, int page, int size) {
        validarPertenenciaEmpleadoARestaurante(restauranteId);
        return pedidoPersistencePort.findPedidosPorEstadoYRestaurante(estado, restauranteId, PageRequest.of(page, size));
    }

    @Override
    public Pedido asignarEmpleadoYPonerEnPreparacion(Long idPedido, Long idEmpleado) {
        Pedido pedido = pedidoPersistencePort.findById(idPedido);
        if (pedido == null) {
            throw new BusinessException("Pedido no encontrado.");
        }

        validarPertenenciaEmpleadoARestaurante(pedido.getIdRestaurante());
        validarRolEmpleado(idEmpleado);

        pedido.setEmpleadoAsignado(idEmpleado);
        pedido.setEstado(EstadoPedido.EN_PREPARACION);

        return pedidoPersistencePort.savePedido(pedido);
    }

    @Override
    public Pedido marcarPedidoComoListo(Long idPedido) {
        Pedido pedido = pedidoPersistencePort.findById(idPedido);
        if (pedido == null) {
            throw new BusinessException("Pedido no encontrado.");
        }

        if (!EstadoPedido.EN_PREPARACION.equals(pedido.getEstado())) {
            throw new BusinessException("Solo los pedidos en preparación pueden ser marcados como listos.");
        }

        pedido.setEstado(EstadoPedido.LISTO);
        pedidoPersistencePort.savePedido(pedido);

        String celular = usuarioServicePort.obtenerTelefonoCliente(pedido.getIdCliente());
        String mensaje = "Tu pedido está listo. Tu pin de recogida es: " + pedido.getPin();


        mensajeriaService.enviarNotificacion(celular, mensaje);

        return pedido;
    }



    private void validarPedido(Pedido pedido) {
        if (pedido.getIdRestaurante() == null) {
            throw new BusinessException("El pedido debe especificar un restaurante.");
        }

        if (pedido.getItems() == null || pedido.getItems().isEmpty()) {
            throw new BusinessException("El pedido no tiene platos.");
        }

        Long restauranteId = pedido.getIdRestaurante();
        for (PedidoItem item : pedido.getItems()) {
            if (!restauranteId.equals(item.getIdRestaurante())) {
                throw new BusinessException("Todos los platos deben ser del mismo restaurante.");
            }
            if (item.getCantidad() <= 0) {
                throw new BusinessException("La cantidad de cada plato debe ser mayor a cero.");
            }
        }
    }

    private void validarPertenenciaEmpleadoARestaurante(Long restauranteId) {
        if (!restaurantePersistencePort.elEmpleadoPerteneceAlRestaurante(restauranteId)) {
            throw new BusinessException("El empleado no pertenece a este restaurante.");
        }
    }

    private void validarRolEmpleado(Long idEmpleado) {
        String rol = usuarioServicePort.obtenerRolUsuario(idEmpleado);
        if (!"EMPLEADO".equalsIgnoreCase(rol)) {
            throw new BusinessException("El usuario no tiene el rol de empleado.");
        }
    }
}
