package com.demo.userreg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.userreg.dto.ImageRequest;
import com.demo.userreg.exception.ServiceException;
import com.demo.userreg.service.ImageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/user/image")
@Validated
public class ImageController {
	
	@Autowired
	private ImageService imageService;

	@Operation(summary = "View all images of a user", description = "This endpoint checks for username and password match and returns the images of the user")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Images retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Data not found"),
			@ApiResponse(responseCode = "400", description = "Missing or Invalid Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@GetMapping("/view")
	public ResponseEntity<Object> viewImage(@RequestBody(required = true) ImageRequest imageRequest)
			throws ServiceException {
		return new ResponseEntity<>(imageService.viewImages(imageRequest), HttpStatus.OK);
	}
	
	@Operation(summary = "Upload image for a user", description = "This endpoint checks for username and password match and uploads image by the given image id and album id")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Image uploaded successfully"),
			@ApiResponse(responseCode = "404", description = "Data not found"),
			@ApiResponse(responseCode = "400", description = "Missing or Invalid Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@PostMapping("/upload")
	public ResponseEntity<Object> uploadImage(@RequestBody(required = true) ImageRequest imageRequest) throws ServiceException{
		return new ResponseEntity<>(imageService.uploadImage(imageRequest), HttpStatus.OK);
	}
	
	@Operation(summary = "Delete image for a user", description = "This endpoint checks for username and password match and deletes image by the given image id")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Image deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Data not found"),
			@ApiResponse(responseCode = "400", description = "Missing or Invalid Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@DeleteMapping("/delete")
	public ResponseEntity<Object> deleteImage(@RequestBody(required = true) ImageRequest imageRequest) throws ServiceException{
		return new ResponseEntity<>(imageService.deleteImage(imageRequest), HttpStatus.OK);
	}
	
}
