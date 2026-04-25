package com.pavan.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavan.jobportal.dto.LoginRequest;
import com.pavan.jobportal.dto.LoginResponse;
import com.pavan.jobportal.dto.UserRequest;
import com.pavan.jobportal.enums.Role;
import com.pavan.jobportal.response.ApiResponse;
import com.pavan.jobportal.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// Api to user register

	@PostMapping("/register/jobseeker")
	public ResponseEntity<ApiResponse<String>> registerJobSeeker(@Valid @RequestBody UserRequest request) {

		String result = userService.registerUser(request, Role.JOBSEEKER);

		ApiResponse<String> response = new ApiResponse<String>("User Created Sucessfully..", result,
				HttpStatus.CREATED.value());

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/register/recruiter")
	public ResponseEntity<ApiResponse<String>> registerRecruiter(@Valid @RequestBody UserRequest request) {

		String result = userService.registerUser(request, Role.RECRUITER);

		ApiResponse<String> response = new ApiResponse<String>("User Created Sucessfully..", result,
				HttpStatus.CREATED.value());

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// Api to User login

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<?>> login(@RequestBody LoginRequest request) {

		LoginResponse result = userService.loginUser(request);

		ApiResponse<LoginResponse> response = new ApiResponse<>("Login Successful", result, HttpStatus.OK.value());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
