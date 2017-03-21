package com.capgemini.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.entity.EmployeeEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeValidationServiceTest {

	@Autowired
	private EmployeeValidationService validationService;
	@Autowired
	private EmployeeService employeeService;

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenNewUserAlreadyExist() {
		// given
		EmployeeEntity employee = employeeService.findAllEmployees().get(0);

		// when
		validationService.checkIfEmployeeExist(employee.getSocialSecurity());

		// then
	}

	@Test(expected = IllegalStateException.class)
	public void shouldThrowIllegalStateExceptionWhenUserToRemoveIsActive() {
		// given
		EmployeeEntity employee = employeeService.findEmployeeById(1L);

		// when
		validationService.checkIfEmployeeIsActive(employee.getId());

		// then
	}

}
