package com.eriklima.desafio.service;
import java.util.List;
import java.util.Optional;
import com.eriklima.desafio.entity.Pedido;

public interface PedidoService {

	
	/**
	 * Realiza a busca dos pedidos no banco de dados.
	 * @return List<Pedido>
	 */
	List<Pedido> buscarPedidos();

	/**
	 * Realiza a busca dos pedidos no banco de dados.
	 * @param Long
	 * @return List<Pedido>
	 */
	Optional<Pedido> buscarPedidoPeloId(Long idDoPedido);


	/**
	 * Realiza o cadastro de Pedido.
	 * @param Pedido
	 * @return Pedido
	 */
	Pedido cadastrar(Pedido pedido);
}
