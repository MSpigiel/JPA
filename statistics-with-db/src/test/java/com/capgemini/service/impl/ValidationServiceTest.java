package com.capgemini.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.service.ValidationService;
import com.capgemini.service.to.UserTO;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationServiceTest {

	@Autowired
	ValidationService validationService;
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenPasswordTooShort() {
		//given
		UserTO user = new UserTO();
		// when
		user.setPassword("pass");
		//then
		validationService.validateUser(user);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenPasswordTooLong() {
		//given
		UserTO user = new UserTO();
		// when
		user.setPassword("passpasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspasspass");
		//then
		validationService.validateUser(user);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionWhenEmailFormatNotCorrect() {
		//given
		UserTO user = new UserTO();
		// when
		user.setPassword("password123123");
		user.setEmail("sasasas");
		//then
		validationService.validateUser(user);
	}
	
	@Test
	public void shouldReturnTrueWhenEmailAndPasswordOk() {
		//given
		UserTO user = new UserTO();
		// when
		user.setPassword("password123123");
		user.setEmail("email@email.com");
		//then
		validationService.validateUser(user);
	}

}
