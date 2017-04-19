package com.capgemini.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.DepartmentDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.entity.DepartmentEntity;
import com.capgemini.entity.EmployeeEntity;
import com.capgemini.service.AssignmentService;
import com.capgemini.service.EmployeeService;
import com.capgemini.service.EmployeeValidationService;

@Service
@Transactional(readOnly = true)
public class EmploeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private EmployeeValidationService employeeValidateService;
	@Autowired
	private AssignmentService assignmentService;

	@Override
	@Transactional(readOnly = false)
	public EmployeeEntity addNewEmployee(String name, String lastName, String socialSecurity, String birthDate,
			String homeNumber, String mobileNumber, String email, Long departmentId) {
		if (employeeValidateService.checkIfEmployeeExist(socialSecurity)) {
			return null;
		}
		EmployeeEntity newEmployee;
		try {
			newEmployee = buildEmployee(name, lastName, socialSecurity, birthDate, homeNumber, mobileNumber, email,
					departmentId);
			return employeeDao.save(newEmployee);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteEmployee(Long id) {
		if (employeeValidateService.checkIfEmployeeIsActive(id)) {
			return;
		} else
			employeeDao.delete(id);
		assignmentService.deleteAllAssignmentsOfId(id);
	}

	@Override
	@Transactional(readOnly = false)
	public EmployeeEntity updateEmployee(EmployeeEntity employee) {
		return employeeDao.update(employee);
	}

	@Override
	@Transactional(readOnly = false)
	public void assignEmployeeToDepartment(Long employeeId, Long departmentId) {
		EmployeeEntity employee = employeeDao.getOne(employeeId);
		DepartmentEntity department = departmentDao.getOne(departmentId);
		employee.setDepartment(department);
		updateEmployee(employee);
	}

	@Override
	public List<EmployeeEntity> findEmployeeByName(String name) {
		return employeeDao.findEmployeeByName(name);
	}

	@Override
	public List<EmployeeEntity> findEmployeeByLastName(String lastName) {
		return employeeDao.findEmployeeByLastName(lastName);
	}

	@Override
	public List<EmployeeEntity> findEmployeeByNameAndLastName(String name, String lastName) {
		return employeeDao.findEmployeeByNameAndLastName(name, lastName);
	}

	@Override
	public EmployeeEntity findEmployeeById(Long id) {
		return employeeDao.findOne(id);
	}

	@Override
	public List<EmployeeEntity> findEmployeeByDepartmentId(Long id) {
		return employeeDao.findEmployeeByDepartmentId(id);
	}

	@Override
	public List<EmployeeEntity> findEmployeeByDepartmentName(String name) {
		return employeeDao.findEmployeeByDepartmentName(name);
	}

	@Override
	public List<EmployeeEntity> findAllEmployees() {
		return employeeDao.findAll();
	}

	private EmployeeEntity buildEmployee(String name, String lastName, String socialSecurity, String birthDate,
			String homeNumber, String mobileNumber, String email, Long departmentId) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(birthDate);
		EmployeeEntity newEmployee = new EmployeeEntity();
		newEmployee.setEmployee_name(name);
		newEmployee.setEmployee_lastName(lastName);
		newEmployee.setSocialSecurity(socialSecurity);
		newEmployee.setBirthDate(date);
		newEmployee.setEmail(email);
		newEmployee.setMobileNumber(mobileNumber);
		newEmployee.setHomeNumber(homeNumber);
		newEmployee.setDepartment(departmentDao.findOne(departmentId));
		return newEmployee;
	}

}
