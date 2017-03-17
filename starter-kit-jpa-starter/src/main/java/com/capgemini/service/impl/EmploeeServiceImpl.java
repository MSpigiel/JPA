package com.capgemini.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.DepartmentDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.entity.DepartmentEntity;
import com.capgemini.entity.EmployeeEntity;
import com.capgemini.service.EmployeeService;

@Service
@Transactional(readOnly=true)
public class EmploeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	@Transactional(readOnly=false)
	public EmployeeEntity addNewEmployee(String name, String lastName, String socialSecurity, String birthDate,
			Long departmentId) {
		EmployeeEntity newEmployee = buildEmployee(name, lastName, socialSecurity, birthDate, departmentId);
		return employeeDao.save(newEmployee);
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteEmployee(Long id) {
		employeeDao.delete(id);
	}

	@Override
	@Transactional(readOnly=false)
	public EmployeeEntity updateEmployee(EmployeeEntity employee) {

		return employeeDao.update(employee);
	}

	@Override
	@Transactional(readOnly=false)
	public void assignEmployeeToDepartment(Long employeeId, Long departmentId) {
		EmployeeEntity employee = employeeDao.getOne(employeeId);
		DepartmentEntity department = departmentDao.getOne(departmentId);
		employee.setDepartment(department);
		updateEmployee(employee);
	}

	@Override
	public EmployeeEntity findByNameAndLastName(String name, String lastName) {
		return employeeDao.findEmployeeByNameAndLastName(name, lastName);
	}
	
	private EmployeeEntity buildEmployee(String name, String lastName, String socialSecurity, String birthDate,
			Long departmentId) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime dateTime = LocalDateTime.parse(birthDate, formatter);
		EmployeeEntity newEmployee = new EmployeeEntity();
		newEmployee.setEmployee_name(name);
		newEmployee.setEmployee_lastName(lastName);
		newEmployee.setSocialSecurity(socialSecurity);
		newEmployee.setBirthDate(dateTime);
		newEmployee.setDepartment(departmentDao.findOne(departmentId));
		return newEmployee;
	}

	

}
