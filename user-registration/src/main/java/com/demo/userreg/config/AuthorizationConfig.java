package com.demo.userreg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	private AuthenticationManager authenticationManager;
	private ConfigProperties configProperties;
	
	@Autowired
	public AuthorizationConfig(AuthenticationManager authenticationManager, ConfigProperties configProperties) {
		super();
		this.authenticationManager = authenticationManager;
		this.configProperties = configProperties;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(configProperties.getClientId()).secret(configProperties.getClientSecret())
				.authorizedGrantTypes("client_credentials").scopes("resource-server-read", "resource-server-write");
	}
	
	
}
