package com.demo.userreg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.userreg.dto.ErrorResponse;
import com.sangupta.jerry.http.WebRequest;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(ServiceException.class)
	public ResponseEntity<Object> handleServiceException(Exception ex, WebRequest request) {
		var errorDetails = getErrorResponseObject(HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	private Object getErrorResponseObject(String code, String message) {
		return new ErrorResponse(code, message, false);
	}

}
