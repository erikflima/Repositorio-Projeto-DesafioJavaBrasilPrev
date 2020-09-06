package com.eriklima.desafio.service;
import java.util.List;
import java.util.Optional;

import com.eriklima.desafio.entity.Cliente;

public interface ClienteService {

	/**
	 * Realiza o cadastro de cliente.
	 * @param Cliente
	 * @return Cliente
	 */
	Cliente cadastrar(Cliente cliente);
	
	
	/**
	 * Retorna todos os clientes do banco de dados.
	 * @return List<Cliente>
	 */
	List<Cliente> buscarClientes();	
	

	/**
	 * Retorna o cliente de acordo com o id recebido.
	 * @param Long
	 * @return Optional<Cliente>
	 */
	Optional<Cliente> buscarClientePeloId(Long idDoCliente);
}