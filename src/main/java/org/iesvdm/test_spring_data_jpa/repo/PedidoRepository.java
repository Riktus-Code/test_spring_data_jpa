package org.iesvdm.test_spring_data_jpa.repo;

import org.iesvdm.test_spring_data_jpa.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    public List<Pedido> findAllByOrderByFechaHoraDesc();

    List<Pedido> findTop2ByOrderByCantidadDesc();

    List<Pedido> findByFechaHoraBetweenAndCantidadGreaterThan(LocalDateTime inicio,LocalDateTime fin, Double cantidad);


}
