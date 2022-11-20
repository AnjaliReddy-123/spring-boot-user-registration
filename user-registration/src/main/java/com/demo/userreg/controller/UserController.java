package com.demo.userreg.controller;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.userreg.dto.UserDto;
import com.demo.userreg.exception.ServiceException;
import com.demo.userreg.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;
	
	@Operation(summary = "Registers an user", description = "This endpoint registers an user with the given user details")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "User registered successfully"),
			@ApiResponse(responseCode = "404", description = "Data not found"),
			@ApiResponse(responseCode = "400", description = "Missing or Invalid Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@RequestBody(required = true) UserDto user) throws ServiceException{
		return new ResponseEntity<>(userService.registerUser(user), HttpStatus.OK);
	}
	
	@Operation(summary = "Update user information", description = "This endpoint updates the user by id with the given details")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "User details updated successfully"),
			@ApiResponse(responseCode = "404", description = "Data not found"),
			@ApiResponse(responseCode = "400", description = "Missing or Invalid Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@PostMapping("/update")
	public ResponseEntity<Object> updateUser(@RequestBody(required = true) UserDto user) throws ServiceException{
		return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
	} 
	
	@Operation(summary = "Delete user", description = "This endpoint deletes the user by id")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "User deleted successfully"),
			@ApiResponse(responseCode = "404", description = "Data not found"),
			@ApiResponse(responseCode = "400", description = "Missing or Invalid Request"),
			@ApiResponse(responseCode = "500", description = "Internal Server Error") })
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable(required = true) @Positive int userId) throws ServiceException{
		return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
	}
	
}
