package com.capgemini.service;

public interface EmployeeValidationService {
	
	public boolean checkIfEmployeeExist(String socialSecurity);
	public boolean checkIfEmployeeIsActive(Long id);
	
}
