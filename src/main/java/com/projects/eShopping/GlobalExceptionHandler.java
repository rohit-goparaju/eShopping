package com.projects.eShopping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentInvalid(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error-> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> generalException(Exception ex){
		Map<String, String> error = new HashMap<>();
		error.put("Exception", ex.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}	
	
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<Map<String, String>> jwtExpiredException(ExpiredJwtException ex){
		Map<String, String> error = new HashMap<>();
		error.put("JWTExpired", ex.getMessage());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
	
}
