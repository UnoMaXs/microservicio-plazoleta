package com.plazoleta.infrastructure.output.jpa.repository;

import com.plazoleta.domain.model.EstadoPedido;
import com.plazoleta.domain.model.Pedido;
import com.plazoleta.infrastructure.output.jpa.entity.PedidoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPedidoRepository extends JpaRepository<PedidoEntity, Long> {
    boolean existsByIdClienteAndEstadoIn(Long idCliente, List<EstadoPedido> estados);
    Page<PedidoEntity> findByEstadoAndIdRestaurante(EstadoPedido estado, Long restauranteId, PageRequest pageRequest);

}
