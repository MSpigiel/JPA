package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.EmployeeEntity;

public interface EmployeeService {

	public EmployeeEntity addNewEmployee(String name, String lastName, String socialSecurity, String birthDate,
			String homeNumber, String mobileNumber, String email, Long departmentId);	
	public void deleteEmployee(Long id);
	public void assignEmployeeToDepartment(Long employeeId, Long departmentId);
	public EmployeeEntity updateEmployee(EmployeeEntity employee);
	public EmployeeEntity findEmployeeById(Long id);
	public List<EmployeeEntity> findEmployeeByName(String name);
	public List<EmployeeEntity> findEmployeeByLastName(String lastName);
	public List<EmployeeEntity> findEmployeeByNameAndLastName(String name, String lastName);
	public List<EmployeeEntity> findEmployeeByDepartmentId(Long id);
	public List<EmployeeEntity> findEmployeeByDepartmentName(String name);
	public List<EmployeeEntity> findAllEmployees();
}
