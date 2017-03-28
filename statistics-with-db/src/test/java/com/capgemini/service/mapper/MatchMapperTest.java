package com.capgemini.service.mapper;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dataaccess.entities.MatchEntity;
import com.capgemini.dataaccess.entities.ProfileEntity;
import com.capgemini.dataaccess.entities.StatisticsEntity;
import com.capgemini.dataaccess.entities.UserEntity;
import com.capgemini.service.to.MatchTO;
import com.capgemini.service.to.ProfileTO;
import com.capgemini.service.to.StatisticsTO;
import com.capgemini.service.to.UserTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MatchMapperTest {

	@Test
	public void shouldMapMatchEntityToMatchTo() {
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

		MatchEntity match = new MatchEntity(user, user2);
		match.setId(1L);
		// when
		MatchTO matchTo = MatchMapper.map(match);
		// then
		assertTrue(matchTo.getWinner().getClass() == UserTO.class);
		assertTrue(matchTo.getLooser().getStatistics().getClass() == StatisticsTO.class);
	}
	
	@Test
	public void shouldMapMatchToToMatchEntity() {
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

		MatchTO match = new MatchTO(user, user2);

		// when
		MatchEntity matchEntity = MatchMapper.map(match);
		// then
		assertTrue(matchEntity.getWinner().getClass() == UserEntity.class);
		assertTrue(matchEntity.getLooser().getStatistics().getClass() == StatisticsEntity.class);
	}

}
