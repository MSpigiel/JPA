package com.capgemini.service;

import com.capgemini.service.to.UserTO;

public interface UserService {
	
	public UserTO readUser(Long id);
	public UserTO updateUser(UserTO user);
}
