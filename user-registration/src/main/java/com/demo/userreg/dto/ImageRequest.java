package com.demo.userreg.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Validated
public class ImageRequest {

	@NotBlank
	private String userName;

	@NotBlank
	private String password;
	
	@NotBlank
	private String albumId;
	
	private Object image;
	
	private String imageId;
	
	private String imageType;
	
	private String imageName;
	
	private String imageTitle;
	
	private String imageDescription;

}
