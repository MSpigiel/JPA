package com.capgemini.service;

import com.capgemini.service.to.UserTO;

public interface ValidationService {
	
	public boolean validateUser(UserTO user);
	
}
