package com.eriklima.desafio.controller;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eriklima.desafio.dto.ClienteDto;
import com.eriklima.desafio.entity.Cliente;
import com.eriklima.desafio.response.MensagemPadrao;
import com.eriklima.desafio.response.ResponsePadronizado;
import com.eriklima.desafio.response.TipoMensagemEnum;
import com.eriklima.desafio.service.ClienteService;
import com.eriklima.desafio.util.ValidadorDeParametrosDeEntrada;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value="/api/cliente", produces="application/json")
@CrossOrigin(origins = "*") 
@Api(value="Language API", description="Controller responsável pelos endpoints relacionados aos Clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	
    public ClienteController(){
    }    
    
    
	@ApiOperation     (value = "Realiza o cadastro de um cliente") 
	@ApiResponses     (value = { @ApiResponse(code = 500, message = "Internal Server Error", response = ResponsePadronizado.class),
			                     @ApiResponse(code = 401, message = "Unauthorized") })	
	@PostMapping(value = "/cadastrar")    
	public ResponseEntity< ResponsePadronizado<Cliente> > cadastrarCliente( @Valid @RequestBody ClienteDto    clienteDto, 
                                                                                                BindingResult resultadoDaValidacao ){

		ResponsePadronizado<Cliente> responsePadronizado = new ResponsePadronizado<Cliente>();
		

		//Validacoes gerais do dto.
		boolean resultadoDaValidacaoContemErros = ValidadorDeParametrosDeEntrada.verificarResultadoDaValidacaoDoBindingResult( resultadoDaValidacao, responsePadronizado );
		if( resultadoDaValidacaoContemErros ){
		
			return ResponseEntity.ok().body( responsePadronizado );
		}

	
		try {		
			
			Cliente cliente = converterClienteDtoParaCliente( clienteDto );
			
			Cliente clienteCadastrado = clienteService.cadastrar(cliente);
			
			responsePadronizado.setConteudoDoResponse( clienteCadastrado );
			
			return ResponseEntity.ok().body( responsePadronizado );		
		
		
		
		} catch ( Exception exceptionOcorrida ){
			
			MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.PROBLEMA_VERMELHO.getTipoDeMensagem(), exceptionOcorrida.getMessage() );

			responsePadronizado.getMensagens().add( mensagemPadrao );
			
		  }
		
			return ResponseEntity.status(500).body(responsePadronizado);
			
		}


	@ApiOperation     (value = "Realiza a busca de todos os clientes do banco de dados") 
	@ApiResponses     (value = { @ApiResponse(code = 500, message = "Internal Server Error", response = ResponsePadronizado.class),
			                     @ApiResponse(code = 401, message = "Unauthorized") })	
	@GetMapping(value = "/buscarClientes")    
	public ResponseEntity< ResponsePadronizado< List<Cliente> > > buscarClientes(){

		
		ResponsePadronizado< List<Cliente> > responsePadronizado = new ResponsePadronizado< List<Cliente> >();
		
	
		try {		

			List<Cliente> listaDeClientes = clienteService.buscarClientes();
			
			responsePadronizado.setConteudoDoResponse( listaDeClientes );
			
			return ResponseEntity.ok().body( responsePadronizado );		
		
		
		
		} catch ( Exception exceptionOcorrida ){
			
			MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.PROBLEMA_VERMELHO.getTipoDeMensagem(), exceptionOcorrida.getMessage() );

			responsePadronizado.getMensagens().add( mensagemPadrao );
			
		  }
		
			return ResponseEntity.status(500).body(responsePadronizado);
			
		}



	@ApiOperation     (value = "Realiza a busca de um cliente pelo seu número de ID") 
	@ApiResponses     (value = { @ApiResponse(code = 500, message = "Internal Server Error", response = ResponsePadronizado.class),
			                     @ApiResponse(code = 401, message = "Unauthorized") })	
	@GetMapping(value = "/buscarClientePeloId/{idDoCliente}")    
	public ResponseEntity<ResponsePadronizado< Cliente> > buscarClientePeloId( @PathVariable("idDoCliente") Long idDoCliente){

		
		ResponsePadronizado<Cliente> responsePadronizado = new ResponsePadronizado< Cliente >();
		
	
		try {		

			Optional<Cliente> cliente = clienteService.buscarClientePeloId( idDoCliente );
			
			if ( !cliente.isPresent() ) {
				
				
				return ResponseEntity.status(404).body(responsePadronizado);
			}
			
			responsePadronizado.setConteudoDoResponse( cliente.get() );
			
			return ResponseEntity.ok().body( responsePadronizado );		
		
		
		} catch ( Exception exceptionOcorrida ){
			
			MensagemPadrao mensagemPadrao = new MensagemPadrao( TipoMensagemEnum.PROBLEMA_VERMELHO.getTipoDeMensagem(), exceptionOcorrida.getMessage() );

			responsePadronizado.getMensagens().add( mensagemPadrao );
			
		  }
		
			return ResponseEntity.status(500).body(responsePadronizado);
			
	}



	/**
	 * Converte um objeto do tipo ClienteDto para Cliente.
	 * @param  ClienteDto
	 * @return Cliente
	 */
	private Cliente converterClienteDtoParaCliente(ClienteDto clienteDto) {

		Cliente cliente = new Cliente();
		
		cliente.setCpf( clienteDto.getCpf() );
		cliente.setNome( clienteDto.getNome() );		
		
		return cliente;
	}

}