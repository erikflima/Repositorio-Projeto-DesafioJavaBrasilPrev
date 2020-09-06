package com.eriklima.desafio.response;
//Referencia: https://getbootstrap.com/docs/4.2/components/alerts/


public enum TipoMensagemEnum {
	
	
	AFIRMATIVA_VERDE("AFIRMATIVA_VERDE"),
	INFORMATIVA_AZUL("INFORMATIVA_AZUL"),
	ALERTA_AMARELO("ALERTA_AMARELO"),
	PROBLEMA_VERMELHO("PROBLEMA_VERMELHO"),
	ERRO_DE_VALIDACAO_DTO("ERRO_DE_VALIDACAO_DTO");


	//-------------------------Constructors-------------------------------//
	
	TipoMensagemEnum( String tipoDeMensagem){
		
		this.tipoDeMensagem = tipoDeMensagem;
	}
	
	
	private String tipoDeMensagem;
	
	
	//------------------------------Getters and Setters-------------------//
	
	public String getTipoDeMensagem() {
		
		return tipoDeMensagem;
	}
	

}