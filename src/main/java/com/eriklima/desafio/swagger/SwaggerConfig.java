package com.eriklima.desafio.swagger;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//URI para visualizar a documentacao em formato HTML "http://localhost:8080/swagger-ui.html#".

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	
	
	@Bean                
	public Docket api(){

		Docket docketASerRetornado = new Docket( DocumentationType.SWAGGER_2 );
		

		return docketASerRetornado.select()
			 	                  .apis(RequestHandlerSelectors.basePackage("com.eriklima.desafio.controller")) //pacotes com as classes controllers do projeto.
				                  .paths( PathSelectors.any() )
				                  .build()
				                  .apiInfo( apiInfo() )
				                  .useDefaultResponseMessages(false); //Elimina as respostas padroes da documentacao visual do swagger.
	}

	
	private ApiInfo apiInfo() {
		
		
		//Definição de texto da pagina da documentação visual.
	    String  title             = "Documentação das api's do projeto'";
	    String  description       = "Lista de endpoints da aplicação com detalhamento";
        String  version           = "1.0";
        String  termsOfServiceUrl = "https://www.eriklima.com";
        String  license           = "Licensa - Todos os direitos reservados.";
        String  licenseUrl        = "http://www.eriklima.com";
        Contact contact           = new Contact("Erik Lima", "https://www.eriklima.com", "erik@eriklima.com");

	
        ApiInfo apiInfo = new ApiInfo( 
        		                       title, 
        		                       description, 
        		                       version, 
        		                       termsOfServiceUrl, 
        		                       contact, 
        		                       license, 
        		                       licenseUrl, 
	                                   Collections.emptyList()
	                                  );
	    return apiInfo;	
	}

}