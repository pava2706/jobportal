package com.pavan.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.jobportal.dto.UserRequest;
import com.pavan.jobportal.response.ApiResponse;
import com.pavan.jobportal.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>> registerUser(@Valid @RequestBody UserRequest request) {

		String result = userService.registerUser(request);

		ApiResponse<String> response = new ApiResponse<String>("User Created Sucessfully..", result,
				HttpStatus.CREATED.value());

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
