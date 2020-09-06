package com.eriklima.desafio.controller;
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

import com.eriklima.desafio.dto.ProdutoDto;
import com.eriklima.desafio.entity.Produto;
import com.eriklima.desafio.response.MensagemPadrao;
import com.eriklima.desafio.response.ResponsePadronizado;
import com.eriklima.desafio.response.TipoMensagemEnum;
import com.eriklima.desafio.service.ProdutoService;
import com.eriklima.desafio.util.ValidadorDeParametrosDeEntrada;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping(value="/api/produto", produces="application/json")
@Api(value="Language API", description="Controller responsável pelos endpoints relacionados aos Produtos")
public class ProdutoController {


	@Autowired
	private ProdutoService produtoService;
	
    public ProdutoController(){
    }    
    
    
	@ApiOperation     (value = "Realiza o cadastro de um produto") 
	@ApiResponses     (value = { @ApiResponse(code = 500, message = "Internal Server Error", response = ResponsePadronizado.class),
			                     @ApiResponse(code = 401, message = "Unauthorized") })	
	@PostMapping(value = "/cadastrar")    
	public ResponseEntity< ResponsePadronizado<Produto> > cadastrarProduto( @Valid @RequestBody ProdutoDto    produtoDto, 
                                                                                                BindingResult resultadoDaValidacao ){

		ResponsePadronizado<Produto> responsePadronizado = new ResponsePadronizado<Produto>();
		

		//Validacoes gerais do dto.
		boolean resultadoDaValidacaoContemErros = ValidadorDeParametrosDeEntrada.verificarResultadoDaValidacaoDoBindingResult( resultadoDaValidacao, responsePadronizado );
		if( resultadoDaValidacaoContemErros ){
		
			return ResponseEntity.badRequest().body( responsePadronizado );
		}

	
		try {		
			
			Produto produto = converterProdutoDtoParaProduto( produtoDto );
			
			Produto produtoCadastrado = produtoService.cadastrar(produto);
			
			responsePadronizado.setConteudoDoResponse( produtoCadastrado );
			
			return ResponseEntity.ok().body( responsePadronizado );		
		
		
		
		} catch ( Exception exceptionOcorrida ){
			
			MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.PROBLEMA_VERMELHO.getTipoDeMensagem(), exceptionOcorrida.getMessage() );

			responsePadronizado.getMensagens().add( mensagemPadrao );
			
		  }
		
			return ResponseEntity.status(500).body(responsePadronizado);
			
		}

 
	@ApiOperation     (value = "Realiza a busca de todos os produtos do banco de dados") 
	@ApiResponses     (value = { @ApiResponse(code = 500, message = "Internal Server Error", response = ResponsePadronizado.class),
			                     @ApiResponse(code = 401, message = "Unauthorized") })	
	@GetMapping(value = "/buscarProdutos")    
	public ResponseEntity< ResponsePadronizado< List<Produto> > > buscarProdutos(){

		
		ResponsePadronizado< List<Produto> > responsePadronizado = new ResponsePadronizado< List<Produto> >();
		
	
		try {		

			List<Produto> listaDeProdutos = produtoService.buscarProdutos();
			
			responsePadronizado.setConteudoDoResponse( listaDeProdutos );
			
			return ResponseEntity.ok().body( responsePadronizado );		
		
		
		
		} catch ( Exception exceptionOcorrida ){
			
			MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.PROBLEMA_VERMELHO.getTipoDeMensagem(), exceptionOcorrida.getMessage() );

			responsePadronizado.getMensagens().add( mensagemPadrao );
			
		  }
		
			return ResponseEntity.status(500).body(responsePadronizado);
			
		}

	
	@ApiOperation     (value = "Realiza a busca de um produto pelo seu número de ID") 
	@ApiResponses     (value = { @ApiResponse(code = 500, message = "Internal Server Error", response = ResponsePadronizado.class),
			                     @ApiResponse(code = 401, message = "Unauthorized") })	
	@GetMapping(value = "/buscarProdutoPeloId/{idDoProduto}")    
	public ResponseEntity<ResponsePadronizado< Produto> > buscarClientePeloId( @PathVariable("idDoProduto") Long idDoProduto){

		
		ResponsePadronizado<Produto> responsePadronizado = new ResponsePadronizado< Produto >();
		
	
		try {		

			Optional<Produto> produto = produtoService.buscarProdutoPeloId( idDoProduto );
			
			if ( !produto.isPresent() ) {
				
				
				return ResponseEntity.status(404).body(responsePadronizado);
			}
			
			responsePadronizado.setConteudoDoResponse( produto.get() );
			
			return ResponseEntity.ok().body( responsePadronizado );		
		
		
		} catch ( Exception exceptionOcorrida ){
			
			MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.PROBLEMA_VERMELHO.getTipoDeMensagem(), exceptionOcorrida.getMessage() );

			responsePadronizado.getMensagens().add( mensagemPadrao );
			
		  }
		
			return ResponseEntity.status(500).body(responsePadronizado);
			
	}
	

	/**
	 * Converte um objeto do tipo ProdutoDto para Produto.
	 * @param  ProdutoDto
	 * @return Produto
	 */
	private Produto converterProdutoDtoParaProduto( ProdutoDto produtoDto ) {

		Produto produto = new Produto();
		
		produto.setNome( produtoDto.getNome() );
		produto.setDescricao( produtoDto.getDescricao() );
		produto.setPrecoUnitario(produtoDto.getPrecoUnitario() );
		produto.setQuantidade(produtoDto.getQuantidade() );
		
		return produto;
	}

}