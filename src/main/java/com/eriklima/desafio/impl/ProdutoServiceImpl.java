package com.eriklima.desafio.impl;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eriklima.desafio.entity.Cliente;
import com.eriklima.desafio.entity.Produto;
import com.eriklima.desafio.repository.ProdutoRepository;
import com.eriklima.desafio.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private static final Logger log = LoggerFactory.getLogger(ProdutoServiceImpl.class);

	
	@Autowired
	private ProdutoRepository produtoRepository;


	@Override
	public Produto cadastrar(Produto produto) {
		
		return produtoRepository.save( produto );
	}

	
	@Override
	public List<Produto> buscarProdutos() {
		
		return produtoRepository.findAll();
	}
	
	@Override
	public Optional<Produto> buscarProdutoPeloId(Long idDoProduto) {	

		
		Optional<Produto> produto = produtoRepository.findById( idDoProduto );
		
		return produto;
	}		
	
	
	
}