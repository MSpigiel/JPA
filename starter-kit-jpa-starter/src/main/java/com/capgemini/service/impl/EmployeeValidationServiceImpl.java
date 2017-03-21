package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dao.AssignmentDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.entity.AssignmentEntity;
import com.capgemini.service.EmployeeValidationService;

@Service
public class EmployeeValidationServiceImpl implements EmployeeValidationService {

	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	AssignmentDao assignmentDao;

	@Override
	public boolean checkIfEmployeeExist(String socialSecurity) {
		if (employeeDao.findEmployeeBySocialSecurity(socialSecurity).size() > 0) {
			throw new IllegalArgumentException("This employee already exist!");
		} else {
			return false;
		}
	}

	@Override
	public boolean checkIfEmployeeIsActive(Long id) {
		List<AssignmentEntity> projects = assignmentDao.findUserActiveProjects(id);
		if(projects.size()>0){
			throw new IllegalStateException("This employee is assigned to active projects, cant be deleted.");
		}
		return false;
	}

}
