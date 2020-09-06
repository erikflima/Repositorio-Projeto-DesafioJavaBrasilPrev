package com.eriklima.desafio.service;
import java.util.List;
import java.util.Optional;
import com.eriklima.desafio.entity.Produto;

public interface ProdutoService {

	/**
	 * Realiza o cadastro de Produto.
	 * @param Produto
	 * @return Produto
	 */
	Produto cadastrar(Produto produto);

	
	/**
	 * Realiza a busca dos produtos no banco de dados.
	 * @return List<Produto>
	 */	
	List<Produto> buscarProdutos();

	
	/**
	 * Realiza o cadastro de Produto.
	 * @param Long
	 * @return Optional<Produto>
	 */
	Optional<Produto> buscarProdutoPeloId(Long idDoProduto);	
}