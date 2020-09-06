package com.eriklima.desafio.dto;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel (description = "Representa os dados necessários de entrada de um Produto.")
public class ProdutoDto {


	@ApiModelProperty(required = true, value = "Nome do produto")
	@NotEmpty(message = "O campo 'nome' é obrigatório, e o mesmo não pode ser vazio ou nulo.")
	private String  nome;
	
	
	@ApiModelProperty(required = true, value = "Descricao do produto")
	@NotEmpty(message = "O campo 'descricao' é obrigatório, e o mesmo não pode ser vazio ou nulo.")
	private String  descricao;
	
	
	@ApiModelProperty(required = true, value = "Quantidade disponível do produto")
	@PositiveOrZero  (message = "O campo 'quantidade' é obrigatório, deve ser um número positivo e o mesmo não pode ser vazio ou nulo.")
	@NotNull         (message = "O campo 'quantidade' é obrigatório, deve ser um número positivo e o mesmo não pode ser vazio ou nulo.")
	private Integer quantidade;
	
	
	@ApiModelProperty(required = true, value = "Preço unitário do produto")
	@Positive(message = "O campo 'precoUnitario' é obrigatório, deve ser um número positivo e o mesmo não pode ser vazio ou nulo.")
	@NotNull (message = "O campo 'precoUnitario' é obrigatório, deve ser um número positivo e o mesmo não pode ser vazio ou nulo.")	
	private Double precoUnitario;
		
	
	public ProdutoDto() {
	}


	//-------------------------Getters and Setters----------------------//
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Double getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(Double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

}