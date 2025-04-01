package com.plazoleta.domain.model;

import java.util.List;

public class Pedido {
    private Long idPedido;
    private Long idCliente;
    private Long idRestaurante;
    private EstadoPedido estado;
    private List<PedidoItem> items;
    private Long empleadoAsignado;
    private String pin;


    public Pedido() {
    }

    public Pedido(Long idPedido, Long idCliente, Long idRestaurante, EstadoPedido estado, List<PedidoItem> items, Long empleadoAsignado, String pin) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idRestaurante = idRestaurante;
        this.estado = estado;
        this.items = items;
        this.empleadoAsignado = empleadoAsignado;
        this.pin = pin;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdRestaurante() {
        return idRestaurante;
    }

    public void setIdRestaurante(Long idRestaurante) {
        this.idRestaurante = idRestaurante;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<PedidoItem> getItems() {
        return items;
    }

    public void setItems(List<PedidoItem> items) {
        this.items = items;
    }

    public Long getEmpleadoAsignado() {
        return empleadoAsignado;
    }

    public void setEmpleadoAsignado(Long empleadoAsignado) {
        this.empleadoAsignado = empleadoAsignado;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
