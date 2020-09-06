package com.eriklima.desafio.dto;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel (description = "Representa os dados dos produtos de de um pedido.")
public class ListaDeProdutosDto {

	
	@ApiModelProperty(required = true, value = "Lista de produtos com dados resumido do pedido")	
	private List<ProdutoResumidoDto> listaProdutosResumido;	
	
}
