package com.eriklima.desafio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eriklima.desafio.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}