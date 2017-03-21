package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.AssignmentDao;
import com.capgemini.entity.AssignmentEntity;
import com.capgemini.entity.EmployeeEntity;
import com.capgemini.service.AssignmentValidationService;

@Service
@Transactional(readOnly = true)
public class AssignmentValidationServiceImpl implements AssignmentValidationService {

	@Autowired
	AssignmentDao assignmentDao;

	@Override
	public boolean checkIfAssignmentExists(Long employeeId, Long projectId) {
		List<AssignmentEntity> assignments = assignmentDao.findSpecifiedAssignment(employeeId, projectId);
		if (assignments.size() > 0) {
			throw new IllegalArgumentException("This employee is already actively assigned to this project!");
		} else
			return false;
	}

	@Override
	public boolean checkIfProjectIsActive(Long projectId) {
		List<EmployeeEntity> activeEmployees = assignmentDao.findActiveProjectMembersById(projectId);
		if (activeEmployees.size() > 0) {
			throw new IllegalStateException("Cant delete project which has active assignments!");
		} else
			return false;
	}

	@Override
	public boolean checkIfAssignmentCanBeClosed(AssignmentEntity assignment) {
		if (assignment.getEnd_date() != null) {
			throw new IllegalStateException("Cant close this assignment, since its already finished");
		} else
			return false;
	}

}
