package com.capgemini.dao;

import com.capgemini.entity.EmployeeEntity;

public interface EmployeeDao extends Dao<EmployeeEntity, Long> {

	public EmployeeEntity findEmployeeByName(String name);
	public EmployeeEntity findEmployeeByLastName(String name);
	public EmployeeEntity findEmployeeByNameAndLastName(String name, String lastName);
	
}
