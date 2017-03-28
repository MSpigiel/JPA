package com.capgemini.service.impl;

import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.service.UserService;
import com.capgemini.service.to.ProfileTO;
import com.capgemini.service.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;
	@PersistenceContext
	private EntityManager em;
	
	@Transactional
	@Test
	public void shouldUpdateUserAndItsProfileCascading() {
		//given
		UserTO user = userService.readUser(1L);
		user.setPassword("updatedPassword");
		ProfileTO profile = user.getProfile();
		profile.setAboutMe("updatedAboutMe");
		user.setProfile(profile);
		// when
		UserTO updatedUser = userService.updateUser(user);
		//then
		assertNotNull(updatedUser.getId());
	}

}
