package com.eriklima.desafio.dto;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel (description = "Representa um produto com dados resumidos.")
public class ProdutoResumidoDto{
	

	@ApiModelProperty(value = "Id do produto")
	@PositiveOrZero  (message = "O campo 'idDoProduto' é obrigatório, deve ser um número positivo e o mesmo não pode ser vazio ou nulo.")
	@NotNull         (message = "O campo 'idDoProduto' é obrigatório, deve ser um número positivo e o mesmo não pode ser vazio ou nulo.")
	private Long idDoProduto;	

	
	@ApiModelProperty(value = "Quantidade disponível do produto")
	@PositiveOrZero  (message = "O campo 'quantidade' é obrigatório, deve ser um número positivo e o mesmo não pode ser vazio ou nulo.")
	@NotNull         (message = "O campo 'quantidade' é obrigatório, deve ser um número positivo e o mesmo não pode ser vazio ou nulo.")
	private Integer quantidade;		


	public ProdutoResumidoDto() {
	}

	//----------------------------------------------//
	
	public Long getIdDoProduto() {
		return idDoProduto;
	}

	public void setIdDoProduto(Long idDoProduto) {
		this.idDoProduto = idDoProduto;
	}


	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}