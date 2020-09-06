package com.eriklima.desafio.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;


@RestController
@RequestMapping(value="/api/pedido", produces="application/json")
@Api(value="Language API", description="Controller responsável pelos endpoints relacionados aos Pedidos")
public class PedidoController {


    private static final Logger log = LoggerFactory.getLogger( PedidoController.class );


    public PedidoController(){
    }    
    
 

}