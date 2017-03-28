package com.capgemini.service.mapper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dataaccess.entities.ProfileEntity;
import com.capgemini.dataaccess.entities.StatisticsEntity;
import com.capgemini.dataaccess.entities.UserEntity;
import com.capgemini.service.to.ProfileTO;
import com.capgemini.service.to.StatisticsTO;
import com.capgemini.service.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

	@Test
	public void shouldMapUserEntityToUserTO() {
		// given
		StatisticsEntity stats = new StatisticsEntity();
		stats.setId(1L);
		ProfileEntity profile = new ProfileEntity();
		profile.setId(1L);
		UserEntity user = new UserEntity("login", "pass", "email@email.com", profile, stats);
		user.setId(1L);

		// when
		UserTO userTo = UserMapper.map(user);

		// then
		assertTrue(userTo.getProfile().getClass() == ProfileTO.class);
		assertTrue(userTo.getStatistics().getClass() == StatisticsTO.class);
		assertTrue(userTo.getId() == user.getId());

	}

	@Test
	public void shouldMapUserTOToUserEntity() {
		// given
		StatisticsTO stats = new StatisticsTO();
		stats.setId(1L);
		ProfileTO profile = new ProfileTO();
		profile.setId(1L);
		UserTO user = new UserTO("login", "pass", "email@email.com", profile, stats);
		user.setId(1L);

		// when
		UserEntity userEntity = UserMapper.map(user);

		// then
		assertTrue(userEntity.getProfile().getClass() == ProfileEntity.class);
		assertTrue(userEntity.getStatistics().getClass() == StatisticsEntity.class);
		assertTrue(userEntity.getId() == user.getId());

	}

	@Test
	public void shouldMapUserEntityListToUserToList() {
		// given
		StatisticsEntity stats = new StatisticsEntity();
		stats.setId(1L);
		ProfileEntity profile = new ProfileEntity();
		profile.setId(1L);
		UserEntity user = new UserEntity("login", "pass", "email@email.com", profile, stats);
		user.setId(1L);

		StatisticsEntity stats2 = new StatisticsEntity();
		stats2.setId(2L);
		ProfileEntity profile2 = new ProfileEntity();
		profile2.setId(2L);
		UserEntity user2 = new UserEntity("login2", "pass2", "email2@email.com", profile2, stats2);
		user2.setId(2L);

		List<UserEntity> userEntityList = new ArrayList<UserEntity>();
		userEntityList.add(user);
		userEntityList.add(user2);

		List<UserTO> userToList = UserMapper.map2TOs(userEntityList);

		assertTrue(userToList.get(0).getClass() == UserTO.class);
		assertTrue(userToList.get(1).getClass() == UserTO.class);

	}

	@Test
	public void shouldMapUserToListToUserEntityList() {
		// given
		StatisticsTO stats = new StatisticsTO();
		stats.setId(1L);
		ProfileTO profile = new ProfileTO();
		profile.setId(1L);
		UserTO user = new UserTO("login", "pass", "email@email.com", profile, stats);
		user.setId(1L);

		StatisticsTO stats2 = new StatisticsTO();
		stats2.setId(2L);
		ProfileTO profile2 = new ProfileTO();
		profile2.setId(2L);
		UserTO user2 = new UserTO("login2", "pass2", "email2@email.com", profile2, stats2);
		user2.setId(2L);

		List<UserTO> userToList = new ArrayList<UserTO>();
		userToList.add(user);
		userToList.add(user2);

		List<UserEntity> userEntityList = UserMapper.map2Entities(userToList);

		assertTrue(userEntityList.get(0).getClass() == UserEntity.class);
		assertTrue(userEntityList.get(1).getClass() == UserEntity.class);

	}

}
