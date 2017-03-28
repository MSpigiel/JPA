package com.capgemini.service.impl;

import org.springframework.stereotype.Service;

import com.capgemini.service.ValidationService;
import com.capgemini.service.to.UserTO;

@Service
public class ValidationServiceImpl implements ValidationService {
	
	@Override
	public boolean validateUser(UserTO user) {
		
		if(user.getPassword().length()<8)
			throw new IllegalArgumentException("Password must contain at least 8 characters.");
		if(user.getPassword().length()>32)
			throw new IllegalArgumentException("Passowrd cannot contain more than 32 characters.");
		if(!user.getEmail().contains("@"))
			throw new IllegalArgumentException("Email must contain @ character.");
		return true;
		
	}	

}
