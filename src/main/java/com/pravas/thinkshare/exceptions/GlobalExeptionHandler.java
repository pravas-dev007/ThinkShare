package com.pravas.thinkshare.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pravas.thinkshare.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExeptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		
		String message=ex.getMessage();
		
		ApiResponse apiRes=new ApiResponse(message, false);
		
		return new ResponseEntity<ApiResponse> (apiRes, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException exp){
		Map<String, String> res=new HashMap<>();
		exp.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError) error).getField();
			String message=error.getDefaultMessage();
			res.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(res, HttpStatus.BAD_REQUEST);
	}
}
