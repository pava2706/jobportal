package com.pavan.jobportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserRequest {

	@NotBlank(message = "Name is Required")
	private String name;

	@Email(message = "Invalid email")
	@NotBlank(message = "Email is Required")
	private String email;

	@Size(min = 6, message = "Password must be at least 6 characters")
	@NotBlank(message = "password is Required")
	private String password;

	@NotBlank(message = "ROle is Required")
	private String role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
