package com.capgemini.dao;

import java.util.List;

import com.capgemini.entity.EmployeeEntity;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

	public List<EmployeeEntity> findEmployeeByName(String name);
	public List<EmployeeEntity> findEmployeeByLastName(String name);
	public List<EmployeeEntity> findEmployeeByNameAndLastName(String name, String lastName);
	public List<EmployeeEntity> findEmployeeByDepartmentId(Long id);
	public List<EmployeeEntity> findEmployeeByDepartmentName(String name);
	public List<EmployeeEntity> findEmployeeBySocialSecurity(String socialSecurity);
	public EmployeeEntity findEmployeeByEmail(String email);
	
}
