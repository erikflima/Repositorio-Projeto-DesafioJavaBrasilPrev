package com.eriklima.desafio.util;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.eriklima.desafio.dto.PedidoDto;
import com.eriklima.desafio.dto.ProdutoResumidoDto;
import com.eriklima.desafio.entity.Cliente;
import com.eriklima.desafio.entity.Pedido;
import com.eriklima.desafio.entity.Produto;
import com.eriklima.desafio.response.MensagemPadrao;
import com.eriklima.desafio.response.ResponsePadronizado;
import com.eriklima.desafio.response.TipoMensagemEnum;
import com.eriklima.desafio.service.ClienteService;
import com.eriklima.desafio.service.ProdutoService;

//Classe com metodos úteis para validação de dados de entrada de metodos.
@Component
public class ValidadorDeParametrosDeEntrada {


	private static final Logger log = LoggerFactory.getLogger( ValidadorDeParametrosDeEntrada.class );
	
	 final static String MSG_ERRO_LOG    = "Há erros da validação no objeto dto recebido:";
	 final static String MSG_ERRO_PADRAO = "Os parametrôs recebidos não estão com a tipagem(texto, númerico, etc) correta. Por favor, verifique o tipo dos parametrôs.";
	

	/**
	* Realiza a validação de um objeto  "BindingResult" recebido, se houver erros de validação registrados, então objeto "ResponsePadronizado<?>" é preparado para que seja possivel retorna-lo como resposta.
	* @param  BindingResult          - Objeto para informar os erros de validação ocorridos.
	* @param  ResponsePadronizado<?> - Resposta padronizada.
	* @return boolean                - 'true' caso exista erros de validação. 'false' caso não existam erros de validação.
	*/
	public static boolean verificarResultadoDaValidacaoDoBindingResult( BindingResult resultadoDaValidacao,  ResponsePadronizado<?> responsePadronizado  ){
		

		//Verificando a validacao dos dados de entrada feita automaticamente pelo Hibernate.
		if ( resultadoDaValidacao.hasErrors() ) {
			
			
			log.error( MSG_ERRO_LOG, resultadoDaValidacao.getAllErrors() );
			
			
			List<ObjectError> listaDeErros = resultadoDaValidacao.getAllErrors();
			
			
			for( ObjectError auxiliar : listaDeErros  ){
				
				//Pegar a mensagem de erro da posicao atual.
				String mensagemDeErroExtraida = auxiliar.getDefaultMessage();	

				//Preparar mensagem de erro os dados recebidos estejam no tipo incorreto.
				if( auxiliar.getCode().equals("typeMismatch") ){
					
					mensagemDeErroExtraida = MSG_ERRO_PADRAO;
				}

				MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.PROBLEMA_VERMELHO.getTipoDeMensagem(), mensagemDeErroExtraida  );

				responsePadronizado.getMensagens().add( mensagemPadrao );

			}
					
			//Foram encontrados erros de validação.
			return true;
		}
		
		//Não foram encontrados erros de validação.
		return false;
	}


	public static boolean validarClienteDoPedido( ResponsePadronizado<Pedido> responsePadronizado, PedidoDto pedidoDto, ClienteService clienteService ){
		
		String MSG_ERRO_CLIENTE = "O id do cliente informado não é valido";
		

			//Etapa de buscar o cliente
			Long idDoCliente = pedidoDto.getIdDoCliente();
			
			
			Optional<Cliente> cliente = clienteService.buscarClientePeloId(idDoCliente);
			
			if ( !cliente.isPresent() ) {

				MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.ALERTA_AMARELO.getTipoDeMensagem(), MSG_ERRO_CLIENTE );

				responsePadronizado.getMensagens().add( mensagemPadrao );				
				
				return true;
			}	
			
		return false;
	}


	
	public static boolean validarProdutosDoPedido(ResponsePadronizado<Pedido> responsePadronizado, PedidoDto pedidoDto, ProdutoService produtoService) {
		
		
		String MSG1_ERRO_PRODUTO = "Id de produto não existente, por favor confira os id's dos produtos que você esta tentando inserir nesse pedido.";

		
		List<ProdutoResumidoDto> listaResumidaDeProdutos = pedidoDto.getListaResumidaDeProdutosDoPedido();
		
		for ( ProdutoResumidoDto produtoResumido : listaResumidaDeProdutos){
			
			
			Optional<Produto> produtoEncontrado = produtoService.buscarProdutoPeloId( produtoResumido.getIdDoProduto() );
			if( !produtoEncontrado.isPresent() ){
				
				MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.ALERTA_AMARELO.getTipoDeMensagem(), MSG1_ERRO_PRODUTO );

				responsePadronizado.getMensagens().add( mensagemPadrao );				
				
				return true;				
				
			}
		}

		return false;
	}	


}