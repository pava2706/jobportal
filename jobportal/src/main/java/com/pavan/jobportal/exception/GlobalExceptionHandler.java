package com.pavan.jobportal.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pavan.jobportal.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Method to handle the user already exist exception

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ApiResponse<Object>> handleUserExists(UserAlreadyExistException ex) {

		ApiResponse<Object> response = new ApiResponse<Object>(ex.getMessage(), null, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// Method to handle to validation exception(name,email,password,role)

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleUserExists(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errors.put(error.getField(), error.getDefaultMessage());
		});

		ApiResponse<Object> response = new ApiResponse<Object>("Validation error", errors,
				HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

	}

	// Method to handle the user not found exception

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse<Object>> handleUserNotFound(UserNotFoundException ex) {

		ApiResponse<Object> response = new ApiResponse<Object>(ex.getMessage(), null, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	// Method to handle the Invalid Password exception

	@ExceptionHandler(InvalidPasswordException.class)
	public ResponseEntity<ApiResponse<Object>> handleUserNotFound(InvalidPasswordException ex) {

		ApiResponse<Object> response = new ApiResponse<Object>(ex.getMessage(), null, HttpStatus.BAD_REQUEST.value());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}