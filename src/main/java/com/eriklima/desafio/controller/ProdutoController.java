package com.eriklima.desafio.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;


@RestController
@RequestMapping(value="/api/produto", produces="application/json")
@Api(value="Language API", description="Controller responsável pelos endpoints relacionados aos Produtos")
public class ProdutoController {


    private static final Logger log = LoggerFactory.getLogger( ProdutoController.class );


    public ProdutoController(){
    }    
    
 

}