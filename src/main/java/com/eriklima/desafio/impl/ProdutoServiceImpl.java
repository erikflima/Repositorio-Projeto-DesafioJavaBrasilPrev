package com.eriklima.desafio.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eriklima.desafio.repository.ProdutoRepository;
import com.eriklima.desafio.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	private static final Logger log = LoggerFactory.getLogger(ProdutoServiceImpl.class);

	
	@Autowired
	private ProdutoRepository produtoRepository;
}