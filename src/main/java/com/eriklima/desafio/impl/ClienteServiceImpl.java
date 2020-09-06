package com.eriklima.desafio.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eriklima.desafio.repository.ClienteRepository;
import com.eriklima.desafio.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

	
	@Autowired
	private ClienteRepository clienteRepository;

}