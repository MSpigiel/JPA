package com.capgemini.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.AssignmentEntity;
import com.capgemini.entity.EmployeeEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssignmentServiceTest {

	@Autowired
	AssignmentService assignmentService;

	@Test
	@Transactional
	public void testShouldFindEmployeesOfActiveProject() {
		// given

		int updatesCounter = 0;
		// when
		updatesCounter = assignmentService.findAllActiveEmployeeOfProject("Book E-Store").size();
		// then

		assertTrue(updatesCounter > 0);
	}

	@Test
	@Transactional
	public void testShouldCreateNewProjectAssignment() {
		// given
		int assignmentCounter = assignmentService.findAllAssignments().size();
		// when
		AssignmentEntity newAssignment = assignmentService.createNewAssignment(5L, 3L, 2L);
		int updatedCounter = assignmentService.findAllAssignments().size();
		// then

		assertTrue(assignmentCounter + 1 == updatedCounter);
		assertNotNull(newAssignment.getId());
	}

	@Test
	@Transactional
	public void testShouldCloseActiveAssignment() {
		// given
		List<AssignmentEntity> assignments = assignmentService.findAllActiveAssignementsOfId(3L);
		int assignmentsCounter = assignments.size();

		// when
		AssignmentEntity assignmentToClose = assignments.get(0);
		assignmentService.closeAssignment(assignmentToClose.getEmployee().getId(),
				assignmentToClose.getProject().getId());
		int updatedAssignmentCounter = assignmentService.findAllActiveAssignementsOfId(3L).size();
		// then

		assertTrue(assignmentsCounter - 1 == updatedAssignmentCounter);
	}

	@Test
	@Transactional
	public void testShouldReturnWithProjectSenorityLongerThan6Months() {
		// given
		List<EmployeeEntity> employees = assignmentService.findEmployeesWithProjectSenorityLongerThanX(1L, 6);
		// when
		int expectedSize = employees.size();
		// then
		assertNotNull(employees.get(0).getId());
		assertTrue(expectedSize == 5);
	}

}
