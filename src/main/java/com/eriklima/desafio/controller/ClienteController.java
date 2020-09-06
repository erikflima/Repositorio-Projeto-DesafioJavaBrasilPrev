package com.eriklima.desafio.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;


@RestController
@RequestMapping(value="/api/cliente", produces="application/json")
@Api(value="Language API", description="Controller responsável pelos endpoints relacionados aos Clientes")
public class ClienteController {


    private static final Logger log = LoggerFactory.getLogger( ClienteController.class );


    public ClienteController(){
    }    
    
 

}