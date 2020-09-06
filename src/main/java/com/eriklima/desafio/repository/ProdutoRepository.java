package com.eriklima.desafio.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.eriklima.desafio.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}