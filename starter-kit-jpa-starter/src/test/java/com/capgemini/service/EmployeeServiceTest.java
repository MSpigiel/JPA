package com.capgemini.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.EmployeeEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private AssignmentService assignmentService;
	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void testFindEmployeesByDepartmentId() {
		// given
		List<EmployeeEntity> employees = employeeService.findEmployeeByDepartmentId(1L);
		// when
		int employeesCount = employees.size();
		// then
		assertNotNull(employees.get(0).getId());
		assertTrue(employeesCount > 0);
	}

	@Test
	@Transactional
	public void testFindEmployeesByDepartmentName() {
		// given
		List<EmployeeEntity> employees = employeeService.findEmployeeByDepartmentName("Sales");
		// when
		int employeesCount = employees.size();
		// then
		assertNotNull(employees.get(0).getId());
		assertTrue(employeesCount > 0);
	}

	@Test
	public void testShouldCreateNewEmployee() {
		// given
		int employeesCount = employeeService.findEmployeeByDepartmentId(2L).size();
		// when
		EmployeeEntity newEmployee = employeeService.addNewEmployee("Boguslaw", "Lindor", "89876290192", "1990-02-01",
				"23143232", "5532321", "bla@bla.pl", 2L);
		// then
		assertNotNull(newEmployee.getId());
		assertTrue(employeesCount + 1 == employeeService.findEmployeeByDepartmentId(2L).size());
	}

	@Test
	@Transactional
	public void testShouldDeleteEmployeeAndHisPastProjects() {
		// given

		int employeeCount = employeeService.findAllEmployees().size();
		int assignmentsCount = assignmentService.findAllAssignments().size();
		int allUserProjectsCount = assignmentService.findAllPastAssignmentsOfId(6L).size();

		// when
		employeeService.deleteEmployee(6L);
		int latestAssignmentsCount = assignmentService.findAllAssignments().size();
		int currentEmployeeCount = employeeService.findAllEmployees().size();

		// then

		assertTrue(employeeCount - 1 == currentEmployeeCount);
		assertTrue(assignmentsCount - allUserProjectsCount == latestAssignmentsCount);
	}

	@Test(expected = ObjectOptimisticLockingFailureException.class)
	@Transactional
	public void shouldThrowOptimisticLockingException() {
		// given
		EmployeeEntity e1 = employeeService.findEmployeeById(1L);
		EmployeeEntity e2 = employeeService.findEmployeeById(1L);
		// when
		em.detach(e2);
		e1.setEmployee_name("NewName");
		employeeService.updateEmployee(e1);
		em.flush();
		e2.setEmployee_name("NewerName");
		// then
		employeeService.updateEmployee(e2);
	}
	
	@Test
	@Transactional
	public void shouldAssignEmployeeToNewDepartment() {
		// given
		employeeService.assignEmployeeToDepartment(1L, 2L);
		Long employeeDepartmentId = employeeService.findEmployeeById(1L).getDepartment().getId();
		// when
		employeeService.assignEmployeeToDepartment(1L, 3L);
		Long updatedEmployeeDepartmentId = employeeService.findEmployeeById(1L).getDepartment().getId();
		//then
		assertTrue(employeeDepartmentId != updatedEmployeeDepartmentId);
	}
	

}
