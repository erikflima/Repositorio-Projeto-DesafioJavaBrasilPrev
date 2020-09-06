package com.eriklima.desafio.dto;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel (description = "Representa os dados necessários de entrada de um Pedido.")
public class PedidoDto {

	
	@ApiModelProperty(required = true, value = "Id do cliente dono do pedido")
	@Positive  (message = "O campo 'idDoCliente' é obrigatório, deve ser um número positivo e o mesmo não pode ser vazio ou nulo.")
	@NotNull         (message = "O campo 'idDoCliente' é obrigatório, deve ser um número positivo e o mesmo não pode ser vazio ou nulo.")
	private Long idDoCliente;
	

	@ApiModelProperty(required = true, value = "Lista de produtos do pedido")
	@NotNull         (message = "O campo 'listaResumidaDeProdutosDoPedido' é obrigatório, essa lista não pode ser nula nem vazia.")
	private List<ProdutoResumidoDto> listaResumidaDeProdutosDoPedido;	
	

	public PedidoDto() {
	}


	//-------------------------Getters and Setters----------------------//
	
	public Long getIdDoCliente() {
		return idDoCliente;
	}

	public void setIdDoCliente(Long idDoCliente) {
		this.idDoCliente = idDoCliente;
	}


	public List<ProdutoResumidoDto> getListaResumidaDeProdutosDoPedido() {
		return listaResumidaDeProdutosDoPedido;
	}

	public void setListaResumidaDeProdutosDoPedido(List<ProdutoResumidoDto> listaResumidaDeProdutosDoPedido) {
		this.listaResumidaDeProdutosDoPedido = listaResumidaDeProdutosDoPedido;
	}

}