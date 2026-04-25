package com.pavan.jobportal.service;

import com.pavan.jobportal.dto.LoginRequest;
import com.pavan.jobportal.dto.LoginResponse;
import com.pavan.jobportal.dto.UserRequest;
import com.pavan.jobportal.enums.Role;

public interface UserService {

	String registerUser(UserRequest request, Role role);

	LoginResponse loginUser(LoginRequest request);

}
