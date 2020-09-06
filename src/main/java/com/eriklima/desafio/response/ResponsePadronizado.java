package com.eriklima.desafio.response;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel (description = "Representa a resposta padrão dos endpoints")
public class ResponsePadronizado<ClasseRecebida> {

	
	@ApiModelProperty(value = "Conteúdo da resposta")
	private ClasseRecebida conteudoDoResponse;
	
	@ApiModelProperty(value = "Lista de mensagens")
	private List<MensagemPadrao> mensagens;

	
	public ResponsePadronizado(){
		super();
	}

	
	//-------------------------Getters and Setters----------------------//	

	public ClasseRecebida getConteudoDoResponse() {
		
		return conteudoDoResponse;
	}

	
	public void setConteudoDoResponse(ClasseRecebida conteudoDoResponse) {
		
		this.conteudoDoResponse = conteudoDoResponse;
	}
	
	
	
	public List<MensagemPadrao> getMensagens() {
		
		if (this.mensagens == null) {
			
			//Nao retorna "null", e sim um objeto vazio.
			this.mensagens = new ArrayList<MensagemPadrao>();
		}
		
		return mensagens;
	}

	
	public void setMensagens( List<MensagemPadrao> mensagens ) {
		
		this.mensagens = mensagens;
	}


}