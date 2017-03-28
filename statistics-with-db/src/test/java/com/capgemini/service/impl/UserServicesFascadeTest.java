//package com.capgemini.service.impl;
//
//import static org.mockito.Matchers.any;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.capgemini.service.UserService;
//import com.capgemini.service.UserServicesFascade;
//import com.capgemini.service.to.ProfileTO;
//import com.capgemini.service.to.StatisticsTO;
//import com.capgemini.service.to.UpdateTO;
//import com.capgemini.service.to.UserTO;
//import org.mockito.AdditionalAnswers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//public class UserServicesFascadeTest {
//
//	@InjectMocks
//	UserServicesFascade fascadeService;
//	
//	@Mock
//	UserService userService;
//	
//	@PersistenceContext
//	private EntityManager em;
//	
//	@Test
//	@Transactional
//	public void test() {
//		
//		Mockito.when(userService.readUser(1L)).thenReturn(giveUser());
//		Mockito.when(userService.updateUser(any(UserTO.class))).then(returnsFirstArg());
//		UpdateTO updateData = new UpdateTO();
//		
//		
//	}
//	
//	private UserTO giveUser(){		
//		StatisticsTO userStatistics = new StatisticsTO();
//		ProfileTO userProfile = new ProfileTO();
//		userProfile.setId(1L);
//		userStatistics.setId(1L);
//		UserTO user = new UserTO("login", "password", "email@email.com", userProfile, userStatistics);
//		user.setId(1L);
//		return user;
//	}
//
//}
