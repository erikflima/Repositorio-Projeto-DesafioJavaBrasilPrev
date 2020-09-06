package com.eriklima.desafio.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eriklima.desafio.repository.PedidoRepository;
import com.eriklima.desafio.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);

	
	@Autowired
	private PedidoRepository pedidoRepository;
}