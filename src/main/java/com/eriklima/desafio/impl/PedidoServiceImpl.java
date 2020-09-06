package com.eriklima.desafio.impl;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eriklima.desafio.entity.Pedido;
import com.eriklima.desafio.repository.PedidoRepository;
import com.eriklima.desafio.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {

	private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);

	
	@Autowired
	private PedidoRepository pedidoRepository;
	

	@Override
	public List<Pedido> buscarPedidos() {

		return pedidoRepository.findAll();
	}

	
	@Override
	public Optional<Pedido> buscarPedidoPeloId(Long idDoPedido) {	

		
		Optional<Pedido> pedido = pedidoRepository.findById( idDoPedido );
		
		return pedido;
	}


	@Override
	public Pedido cadastrar(Pedido pedido) {

		return pedidoRepository.save( pedido );
	}
	
}