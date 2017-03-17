package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.DepartmentEntity;

public interface DepartmentService {

	public DepartmentEntity findDepartmentByName(String name);
	public DepartmentEntity findDepartmentById(Long id);
	public DepartmentEntity createDepartment(String name, String email, String homeNumber, String mobileNumber);
	public List<DepartmentEntity> findAllDepartments();
	public DepartmentEntity updateDepartment(DepartmentEntity department);
}
