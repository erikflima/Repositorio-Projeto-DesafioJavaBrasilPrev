package com.eriklima.desafio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eriklima.desafio.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}