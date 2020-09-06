package com.eriklima.desafio.dto;
import javax.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel (description = "Representa os dados necessários de entrada de um cliente.")
public class ClienteDto {

	@ApiModelProperty(required = true, value = "Nome do cliente")
	@NotEmpty(message = "O campo 'nome' é obrigatório, e o mesmo não pode ser vazio ou nulo.")
	private String  nome;
	
	@ApiModelProperty(required = true, value = "Número de CPF do cliente em formato valido.")
	@CPF
	private String cpf;	
	

	public ClienteDto() {
	}

	//-------------------------Getters and Setters----------------------//
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
