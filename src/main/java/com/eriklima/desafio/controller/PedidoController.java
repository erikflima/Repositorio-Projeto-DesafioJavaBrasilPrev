package com.eriklima.desafio.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eriklima.desafio.dto.PedidoDto;
import com.eriklima.desafio.dto.ProdutoResumidoDto;
import com.eriklima.desafio.entity.Cliente;
import com.eriklima.desafio.entity.Pedido;
import com.eriklima.desafio.entity.Produto;
import com.eriklima.desafio.response.MensagemPadrao;
import com.eriklima.desafio.response.ResponsePadronizado;
import com.eriklima.desafio.response.TipoMensagemEnum;
import com.eriklima.desafio.service.ClienteService;
import com.eriklima.desafio.service.PedidoService;
import com.eriklima.desafio.service.ProdutoService;
import com.eriklima.desafio.util.ValidadorDeParametrosDeEntrada;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value="/api/pedido", produces="application/json")
@Api(value="Language API", description="Controller responsável pelos endpoints relacionados aos Pedidos")
public class PedidoController {


	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;	
	
    
    public PedidoController(){
    }    
    

   	@ApiOperation     (value = "Realiza a busca de todos os pedidos do banco de dados") 
   	@ApiResponses     (value = { @ApiResponse(code = 500, message = "Internal Server Error", response = ResponsePadronizado.class),
   			                     @ApiResponse(code = 401, message = "Unauthorized") })	
   	@GetMapping(value = "/buscarPedidos")    
   	public ResponseEntity< ResponsePadronizado< List<Pedido> > > buscarPedidos(){

   		
   		ResponsePadronizado< List<Pedido> > responsePadronizado = new ResponsePadronizado< List<Pedido> >();
   		
   	
   		try {		

   			List<Pedido> listaDePedidos = pedidoService.buscarPedidos();
   			
   			responsePadronizado.setConteudoDoResponse( listaDePedidos );
   			
   			return ResponseEntity.ok().body( responsePadronizado );		
   		
   		
   		
   		} catch ( Exception exceptionOcorrida ){
   			
   			MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.PROBLEMA_VERMELHO.getTipoDeMensagem(), exceptionOcorrida.getMessage() );

   			responsePadronizado.getMensagens().add( mensagemPadrao );
   			
   		  }
   		
   			return ResponseEntity.status(500).body(responsePadronizado);
   			
   		}

 
	@ApiOperation     (value = "Realiza a busca de um pedido pelo seu número de ID") 
	@ApiResponses     (value = { @ApiResponse(code = 500, message = "Internal Server Error", response = ResponsePadronizado.class),
			                     @ApiResponse(code = 401, message = "Unauthorized") })	
	@GetMapping(value = "/buscarPedidoPeloId/{idDoPedido}")    
	public ResponseEntity<ResponsePadronizado< Pedido> > buscarPedidoPeloId( @PathVariable("idDoPedido") Long idDoPedido){

		
		ResponsePadronizado<Pedido> responsePadronizado = new ResponsePadronizado< Pedido >();
		
	
		try {		

			Optional<Pedido> pedido = pedidoService.buscarPedidoPeloId( idDoPedido );
			
			if ( !pedido.isPresent() ) {
				
				
				return ResponseEntity.status(404).body(responsePadronizado);
			}
			
			responsePadronizado.setConteudoDoResponse( pedido.get() );
			
			return ResponseEntity.ok().body( responsePadronizado );		
		
		
		} catch ( Exception exceptionOcorrida ){
			
			MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.PROBLEMA_VERMELHO.getTipoDeMensagem(), exceptionOcorrida.getMessage() );

			responsePadronizado.getMensagens().add( mensagemPadrao );
			
		  }
		
			return ResponseEntity.status(500).body(responsePadronizado);
			
	}
 

	@ApiOperation     (value = "Realiza o cadastro de um pedido") 
	@ApiResponses     (value = { @ApiResponse(code = 500, message = "Internal Server Error", response = ResponsePadronizado.class),
			                     @ApiResponse(code = 401, message = "Unauthorized") })	
	@PostMapping(value = "/cadastrar")    
	public ResponseEntity< ResponsePadronizado<Pedido> > cadastrarPedido( @Valid @RequestBody PedidoDto     pedidoDto, 
                                                                                               BindingResult resultadoDaValidacao ){

		ResponsePadronizado<Pedido> responsePadronizado = new ResponsePadronizado<Pedido>();
		

		//Validacoes gerais do dto.
		boolean resultadoDaValidacaoContemErros = ValidadorDeParametrosDeEntrada.verificarResultadoDaValidacaoDoBindingResult( resultadoDaValidacao, responsePadronizado );
		if( resultadoDaValidacaoContemErros ){
		
			return ResponseEntity.badRequest().body( responsePadronizado );
		}


		boolean resultadoDaValidacaoDoClienteContemErros = ValidadorDeParametrosDeEntrada.validarClienteDoPedido(responsePadronizado, pedidoDto, clienteService );
		if( resultadoDaValidacaoDoClienteContemErros ){
			
			return ResponseEntity.badRequest().body( responsePadronizado );
		}
		
		
		boolean resultadoDaValidacaoDosProdutosContemErros = ValidadorDeParametrosDeEntrada.validarProdutosDoPedido(responsePadronizado, pedidoDto, produtoService );
		if( resultadoDaValidacaoDosProdutosContemErros ){
			
			return ResponseEntity.badRequest().body( responsePadronizado );
		}		
		
		
		try {	
			
			Pedido pedido = converterPedidoDtoParaPedido( pedidoDto );
			
			Pedido pedidoCadastrado = pedidoService.cadastrar( pedido );
			
			responsePadronizado.setConteudoDoResponse( pedidoCadastrado );
			
			return ResponseEntity.ok().body( responsePadronizado );		
		

		} catch ( Exception exceptionOcorrida ){
			
			MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.PROBLEMA_VERMELHO.getTipoDeMensagem(), exceptionOcorrida.getMessage() );

			responsePadronizado.getMensagens().add( mensagemPadrao );
			
		  }
		
			return ResponseEntity.status(500).body(responsePadronizado);
			
		}


	private Pedido converterPedidoDtoParaPedido(PedidoDto pedidoDto) {

		Pedido pedido = new Pedido();
		
		Optional<Cliente> clienteEncontrado = clienteService.buscarClientePeloId( pedidoDto.getIdDoCliente() );
		pedido.setCliente( clienteEncontrado.get() );
		
		
		List<ProdutoResumidoDto>listaDeProdutosResumidos = pedidoDto.getListaResumidaDeProdutosDoPedido();
		
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		for( ProdutoResumidoDto produtoResumido: listaDeProdutosResumidos){
			
			Optional<Produto> produto = produtoService.buscarProdutoPeloId( produtoResumido.getIdDoProduto() );
			
			produtos.add( produto.get() );
		}

		pedido.setProdutos( produtos );
		

		return pedido;
	}


}