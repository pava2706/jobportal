package com.pavan.jobportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pavan.jobportal.dto.UserRequest;
import com.pavan.jobportal.entity.User;
import com.pavan.jobportal.exception.UserAlreadyExistException;
import com.pavan.jobportal.repository.UserRepository;
import com.pavan.jobportal.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String registerUser(UserRequest request) {

		if (userRepository.existsByEmail(request.getEmail())) {
			throw new UserAlreadyExistException("User already exist with mailId:- " + request.getEmail());
		}

		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setRole(request.getRole());

		userRepository.save(user);

		return " User created";
	}

}
