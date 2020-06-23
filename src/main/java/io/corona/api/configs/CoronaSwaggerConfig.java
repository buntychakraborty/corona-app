package io.corona.api.configs;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration

public class CoronaSwaggerConfig {
	public static final String CORONA_API = "/corona-api.*";
	public static final String IO_CORONA_API_CONTROLLERS = "io.corona.api.controllers";

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(IO_CORONA_API_CONTROLLERS)).paths(regex(CORONA_API)).build();
	}

}
