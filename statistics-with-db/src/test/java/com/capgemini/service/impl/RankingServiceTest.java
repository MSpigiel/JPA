package com.capgemini.service.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.capgemini.dataaccess.dao.UserDao;
import com.capgemini.dataaccess.entities.ProfileEntity;
import com.capgemini.dataaccess.entities.StatisticsEntity;
import com.capgemini.dataaccess.entities.UserEntity;

public class RankingServiceTest {
	@InjectMocks
	RankingServiceImpl rankingService;

	@Mock
	UserDao userDao;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldGetCorrectPlayerPosition() {
		//given
		Mockito.when(userDao.generateFullRanking()).thenReturn(giveUserList());
		//when
		int position = rankingService.getPlayerPostion(1L);
		//then
		assertTrue(position == 1);

	}
	
	@Test
	public void shouldGetCorrectPostionFromPlayerLevelRanking() {
		//given
		Mockito.when(userDao.generateRankingOnUserLevel(1)).thenReturn(giveUserList());
		//when
		int position = rankingService.getPlayerPostionAtPlayerLevel(3L, 1);
		//then
		assertTrue(position == 3);

	}

	
	private List<UserEntity> giveUserList(){
		List<UserEntity> ranking = new ArrayList<UserEntity>();
		
		UserEntity user = new UserEntity();
		StatisticsEntity userStatistics = new StatisticsEntity();
		userStatistics.setId(1L);
		ProfileEntity userProfile = new ProfileEntity();
		userProfile.setId(1L);
		user.setId(1L);
		user.setEmail("a@a.pl");
		user.setLogin("login");
		user.setModificationCounter(0);
		user.setPassword("pass");
		user.setStatistics(userStatistics);
		user.setProfile(userProfile);
		
		UserEntity user2 = new UserEntity();
		StatisticsEntity userStatistics2 = new StatisticsEntity();
		userStatistics2.setId(2L);
		ProfileEntity userProfile2 = new ProfileEntity();
		userProfile2.setId(2L);
		user2.setId(2L);
		user2.setEmail("b@b.pl");
		user2.setLogin("login2");
		user2.setModificationCounter(0);
		user2.setPassword("pass2");
		user2.setStatistics(userStatistics2);
		user2.setProfile(userProfile2);
		
		UserEntity user3 = new UserEntity();
		StatisticsEntity userStatistics3 = new StatisticsEntity();
		userStatistics3.setId(3L);
		ProfileEntity userProfile3 = new ProfileEntity();
		userProfile3.setId(3L);
		user3.setId(3L);
		user3.setEmail("c@c.pl");
		user3.setLogin("login3");
		user3.setModificationCounter(0);
		user3.setPassword("pass3");
		user3.setStatistics(userStatistics3);
		user3.setProfile(userProfile3);
		
		ranking.add(user);
		ranking.add(user2);
		ranking.add(user3);
		
		return ranking;
	}
}

	