package com.portifolio.paymentRest.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket apiDoc() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
					.apis(RequestHandlerSelectors.basePackage("com.portifolio.paymentRest.controller"))
					.paths(PathSelectors.regex("/v1.*"))
					.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		
		return new ApiInfoBuilder()
				.title("paymentRest api by Felipe Gadelha")
				.description("payment processing api")
				.version("1.0")
				.contact(new Contact("Felipe Gadelha Diniz Da Silva", "https://www.linkedin.com/in/felipe-gadelha-diniz-da-silva-aaaa4a158/", "felipegadelha90@gmail.com"))
				.build();
	}

}
