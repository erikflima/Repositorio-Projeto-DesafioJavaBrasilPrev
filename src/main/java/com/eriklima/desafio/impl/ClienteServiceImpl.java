package com.eriklima.desafio.impl;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eriklima.desafio.entity.Cliente;
import com.eriklima.desafio.repository.ClienteRepository;
import com.eriklima.desafio.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

	
	@Autowired
	private ClienteRepository clienteRepository;

	
	@Override
	public Cliente cadastrar( Cliente cliente ) {
		
		return clienteRepository.save( cliente );
	}


	@Override
	public List<Cliente> buscarClientes() {
		
		return clienteRepository.findAll();
	}
	
	
	@Override
	public Optional<Cliente> buscarClientePeloId(Long idDoCliente) {	

		
		Optional<Cliente> cliente = clienteRepository.findById( idDoCliente );
		
		return cliente;
	}	
	
	
}