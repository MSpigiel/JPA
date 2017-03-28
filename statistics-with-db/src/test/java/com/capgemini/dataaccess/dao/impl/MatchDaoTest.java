package com.capgemini.dataaccess.dao.impl;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dataaccess.dao.MatchDao;
import com.capgemini.dataaccess.entities.MatchEntity;
import com.capgemini.dataaccess.entities.UserEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchDaoTest {

	@Autowired
	MatchDao matchDao;

	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void shouldGetCorrectCountOfMatchesPlayedByPlayer() {
		// given
		List<MatchEntity> allMatches = matchDao.findAll();
		UserEntity user = allMatches.get(0).getWinner();
		List<MatchEntity> userMatches = matchDao.getUserMatchHistory(user.getId());
		int userMatchesCount = 0;

		// when
		for (int i = 0; i < allMatches.size(); i++) {
			if (allMatches.get(i).getWinner().getId() == user.getId()
					|| allMatches.get(i).getLooser().getId() == user.getId()) {
				userMatchesCount++;
			}
		}

		// then
		assertTrue(userMatchesCount == userMatches.size());

	}

	@Test
	@Transactional
	public void shouldNotGetAnyMatchesPlayedForNonExistingUser() {
		// given
		List<MatchEntity> userMatches = matchDao.getUserMatchHistory(888L);

		// when
		int expectedMatchesCount = 0;
		int actualMatchesCount = userMatches.size();

		// then
		assertTrue(expectedMatchesCount == actualMatchesCount);
	}

	@Test
	@Transactional
	public void shouldIncreaseMatchedPlayedByUsersByOneAfterGame() {
		// given
		List<MatchEntity> allMatches = matchDao.findAll();
		UserEntity winner = allMatches.get(0).getWinner();
		UserEntity loser = allMatches.get(0).getLooser();
		int winnerTotal = matchDao.getUserMatchHistory(winner.getId()).size();
		int loserTotal = matchDao.getUserMatchHistory(loser.getId()).size();
		
		// when
		MatchEntity match = new MatchEntity(winner, loser);
		matchDao.save(match);

		int winnerTotalActual = matchDao.getUserMatchHistory(winner.getId()).size();
		int loserTotalActual = matchDao.getUserMatchHistory(loser.getId()).size();

		// then
		assertTrue(winnerTotal + 1 == winnerTotalActual);
		assertTrue(loserTotal + 1 == loserTotalActual);
	}

}
