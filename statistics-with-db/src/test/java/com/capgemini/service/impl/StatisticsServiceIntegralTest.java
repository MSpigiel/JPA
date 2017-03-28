package com.capgemini.service.impl;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.LevelRequirements;
import com.capgemini.dataaccess.entities.StatisticsEntity;
import com.capgemini.service.StatisticsService;
import com.capgemini.service.UserService;
import com.capgemini.service.mapper.StatisticsMapper;
import com.capgemini.service.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsServiceIntegralTest {

	@Autowired
	UserService userService;
	@Autowired
	StatisticsService statsService;

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Test
	public void shouldUpdateUserLevelWhenCriteriaMet() {
		// given
		UserTO playerOne = userService.readUser(1L);
		StatisticsEntity stats = new StatisticsEntity(1, 0, 0, 5, 5, 280L, LevelRequirements.NEWBIE);
		stats.setId(playerOne.getStatistics().getId());
		playerOne.setStatistics(StatisticsMapper.map(stats));
		UserTO playerTwo = userService.readUser(2L);
		// when
		statsService.updateStatistics(playerOne, playerTwo);
		em.flush();
		playerOne = userService.readUser(1L);
		// then
		assertTrue(playerOne.getStatistics().getCurrentLevel() == 2);
		assertTrue(playerOne.getStatistics().getMatchWon() == 6);
	}
	
	@Transactional
	@Test
	public void shouldNotUpdateUserLevelWhenCriteriaNotMet() {
		// given
		UserTO playerOne = userService.readUser(1L);
		StatisticsEntity stats = new StatisticsEntity(1, 0, 0, 5, 5, 280L, LevelRequirements.NEWBIE);
		stats.setId(playerOne.getStatistics().getId());
		playerOne.setStatistics(StatisticsMapper.map(stats));
		UserTO playerTwo = userService.readUser(2L);
		// when
		statsService.updateStatistics(playerTwo, playerOne);
		em.flush();
		playerOne = userService.readUser(1L);
		// then
		assertTrue(playerOne.getStatistics().getCurrentLevel() == 1);
		assertTrue(playerOne.getStatistics().getMatchLost() == 1);
	}

	@Transactional
	@Test(expected = ObjectOptimisticLockingFailureException.class)
	public void shouldThrowOptimisticLockingExceptionWhenReceivedUpdateRequestWithIdenticalData() {
		// given
		UserTO winner = userService.readUser(1L);
		UserTO loser = userService.readUser(2L);
		// when
		statsService.updateStatistics(winner, loser);
		em.flush();
		statsService.updateStatistics(winner, loser);
	}

}
