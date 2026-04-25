package com.pavan.jobportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pavan.jobportal.config.JwtUtil;
import com.pavan.jobportal.dto.LoginRequest;
import com.pavan.jobportal.dto.LoginResponse;
import com.pavan.jobportal.dto.UserRequest;
import com.pavan.jobportal.entity.User;
import com.pavan.jobportal.enums.Role;
import com.pavan.jobportal.exception.InvalidPasswordException;
import com.pavan.jobportal.exception.UserAlreadyExistException;
import com.pavan.jobportal.exception.UserNotFoundException;
import com.pavan.jobportal.repository.UserRepository;
import com.pavan.jobportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private JwtUtil jwtUtil;

	@Override
	public String registerUser(UserRequest request, Role role) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistException("User already exist with mailId:- " + request.getEmail());
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(role);

		userRepository.save(user);

		return " User created";
	}

	@Override
	public LoginResponse loginUser(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new UserNotFoundException("Invalid email / Password "));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new InvalidPasswordException("Invalid email / Password ");
		}

		String token = jwtUtil.generateToken(user);

		return new LoginResponse(token, user.getEmail(), user.getRole().name());
	}

}
