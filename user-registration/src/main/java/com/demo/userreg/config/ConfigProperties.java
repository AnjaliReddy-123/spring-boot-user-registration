package com.demo.userreg.config;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Configuration
@ConfigurationProperties(prefix = "app")
@Validated
@Data
@NoArgsConstructor
public class ConfigProperties {
 
	@NotBlank
	private String imgurViewUrl;
	
	@NotBlank
	private String imgurUploadUrl;
	
	@NotBlank
	private String imgurDeleteUrl;
	
	@NotBlank
	private String swaggerUrl;
	
	@Positive
	private Long timeout;
	
	@Value("${spring.security.oauth2:clientId}")
	@NotBlank
	private String clientId;
	
	@Value("${spring.security.oauth2:clientSecret}")
	@NotBlank
	private String clientSecret;
}
