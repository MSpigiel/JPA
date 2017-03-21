package com.capgemini.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.AssignmentEntity;
import com.capgemini.entity.EmployeeEntity;
import com.capgemini.entity.ProjectEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentValidationServiceTest {

	@Autowired
	AssignmentValidationService validationService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	AssignmentService assignmentService;

	@Transactional
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenUserIsAlreadyAssignetToProject() {
		// given
		List<AssignmentEntity> assignments = assignmentService.findAllActiveAssignementsOfId(1L);
		// when
		Long projectId = assignments.get(0).getProject().getId();
		// then
		validationService.checkIfAssignmentExists(1L, projectId);
	}
	
	@Transactional
	@Test(expected = IllegalStateException.class)
	public void shouldThrowIllegalStateExceptionWhenProjectToDeleteIsActive() {
		// given
		List<AssignmentEntity> assignments = assignmentService.findAllActiveAssignments();
		ProjectEntity projectToDelete = assignments.get(0).getProject();
		// when
		Long projectId = projectToDelete.getId();
		// then
		validationService.checkIfProjectIsActive(projectId);
	}
	
	@Transactional
	@Test(expected = IllegalStateException.class)
	public void shouldThrowIllegalStateExceptionWhenProjectToCloseIsAlreadyClosed() {
		// given
		EmployeeEntity employee = employeeService.findEmployeeById(6L);
		List<AssignmentEntity> assignments = assignmentService.findAllPastAssignmentsOfId(employee.getId());
		
		// when
		validationService.checkIfAssignmentCanBeClosed(assignments.get(0));
	}
}
