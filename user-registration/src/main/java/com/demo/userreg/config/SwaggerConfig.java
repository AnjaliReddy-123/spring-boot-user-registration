package com.demo.userreg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	private static final String VERSION = "v1";
	private static final String API_TITLE = "User Registration Demo";
	private ConfigProperties configProperties;
	
	public SwaggerConfig() {
		super();
	}
	
	@Autowired
	public SwaggerConfig(ConfigProperties configProperties) {
		super();
		this.configProperties = configProperties;
	}
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().addServersItem(new Server().url(configProperties.getSwaggerUrl()))
				.info(new Info().title(API_TITLE).version(VERSION));
	}
}
