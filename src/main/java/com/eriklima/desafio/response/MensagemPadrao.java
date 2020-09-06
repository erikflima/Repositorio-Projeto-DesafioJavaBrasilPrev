package com.eriklima.desafio.response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel (description = "Representa uma mensagem padrão")
public class MensagemPadrao {

	@ApiModelProperty(value = "Tipo da mensagem")
	String tipoDeMensagem;
	
	@ApiModelProperty(value = "Mensagem")
    String mensagem;
	
	
	//-------------------------Constructors-------------------------------//	
	
	public MensagemPadrao(){
	}
	
	public MensagemPadrao( String mensagem ){
		
		this.mensagem = mensagem;
	}
	
	public MensagemPadrao( String tipoDeMensagem, String mensagem){
		
		this.tipoDeMensagem = tipoDeMensagem;
		this.mensagem       = mensagem;
	}
	
	
	
	//-------------------------Getters and Setters----------------------//	
	
	public String getTipoDeMensagem(){
		return tipoDeMensagem;
	}

	public void setTipoDeMensagem( String tipoDeMensagem ){
		this.tipoDeMensagem = tipoDeMensagem;
	}

	
	public String getMensagem(){
		return mensagem;
	}

	public void setMensagem( String mensagem ){
		this.mensagem = mensagem;
	}


}