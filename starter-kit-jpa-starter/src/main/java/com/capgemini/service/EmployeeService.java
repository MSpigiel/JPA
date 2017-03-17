package com.capgemini.service;

import com.capgemini.entity.EmployeeEntity;

public interface EmployeeService {

	public EmployeeEntity addNewEmployee(String name, String lastName, String socialSecurity, String birthDate,
			Long departmentId);
	
	public void deleteEmployee(Long id);
	
	public EmployeeEntity updateEmployee(EmployeeEntity employee);
	
	public void assignEmployeeToDepartment(Long employeeId, Long departmentId);
	
	public EmployeeEntity findByNameAndLastName(String name, String lastName);

}
