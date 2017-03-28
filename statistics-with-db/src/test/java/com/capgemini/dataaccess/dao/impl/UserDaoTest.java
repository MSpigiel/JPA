package com.capgemini.dataaccess.dao.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dataaccess.dao.UserDao;
import com.capgemini.dataaccess.entities.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

	@Autowired
	UserDao userDao;
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Test
	public void shouldGetFullRankingSortedDescending() {
		// given
		List<UserEntity> fullRanking = userDao.generateFullRanking();

		int totalUsers = userDao.findAll().size();

		Long points = fullRanking.get(0).getStatistics().getPoints();
		int userCounter = 1;
		boolean isSortedDescending = true;

		// when
		for (int i = 1; i < fullRanking.size(); i++) {
			if (points < fullRanking.get(i).getStatistics().getPoints()) {
				isSortedDescending = false;
			}
			points = fullRanking.get(i).getStatistics().getPoints();
			userCounter++;
		}
		// then
		assertTrue(isSortedDescending);
		assertTrue(totalUsers == userCounter);
	}

	@Transactional
	@Test
	public void shouldGetRankingWithPlayersOnUsersLevel() {
		// given
		UserEntity user = userDao.findOne(1L);
		int userLevel = user.getStatistics().getCurrentLevel();
		List<UserEntity> onUserLevelRanking = userDao.generateRankingOnUserLevel(userLevel);
		boolean isOtherLevelFound = false;
		List<UserEntity> allUsers = userDao.findAll();
		int usersOnDifferentLevel = 0;

		for (int i = 0; i < allUsers.size(); i++) {
			if (allUsers.get(i).getStatistics().getCurrentLevel() != userLevel) {
				usersOnDifferentLevel++;
			}
		}

		// when
		for (int i = 0; i < onUserLevelRanking.size(); i++) {
			if (userLevel != onUserLevelRanking.get(i).getStatistics().getCurrentLevel()) {
				isOtherLevelFound = true;
			} else {

			}
		}
		// then
		assertFalse(isOtherLevelFound);
		assertTrue(usersOnDifferentLevel + onUserLevelRanking.size() == allUsers.size());
	}

	@Transactional
	@Test
	public void shouldFindUserByEmailIfExists() {
		// given
		List<UserEntity> users = userDao.findAll();
		UserEntity userExpected = users.get(0);
		String email = userExpected.getEmail();
		// when
		UserEntity userActual = userDao.findByEmail(email);
		// then
		assertTrue(userExpected.getId() == userActual.getId());
	}

	@Transactional
	@Test(expected = EmptyResultDataAccessException.class)
	public void shouldThrowExceptionWhenEmailIfNotExists() {
		// given
		String email = "not@existing.email";
		// when
		UserEntity userActual = userDao.findByEmail(email);
		// then
		assertNull(userActual);
	}

}
