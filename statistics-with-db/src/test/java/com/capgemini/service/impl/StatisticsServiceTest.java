package com.capgemini.service.impl;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.dataaccess.dao.StatisticsDao;
import com.capgemini.dataaccess.entities.StatisticsEntity;
import com.capgemini.service.to.StatisticsTO;
import com.capgemini.service.to.UserTO;

public class StatisticsServiceTest {

	@InjectMocks
	StatisticsServiceImpl statsService;
	
	@Mock
	StatisticsDao statsDao;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldUpdateWinnerAndLoserStatistics() {
		//given
		statsService.updateStatistics(giveUser(1L), giveUser(2L));
		ArgumentCaptor<StatisticsEntity> userCaptor = ArgumentCaptor.forClass(StatisticsEntity.class);
		verify(statsDao, times(2)).update(userCaptor.capture());
		
		//when
		List<StatisticsEntity> capturedUsers = userCaptor.getAllValues();		
		StatisticsEntity winnerStats = capturedUsers.get(0);
		StatisticsEntity loserStats = capturedUsers.get(1);
				
		//then		
		assertTrue(winnerStats.getMatchTotal() == 1);
		assertTrue(winnerStats.getMatchWon() == 1);
		assertTrue(winnerStats.getPoints() == 40);
		
		assertTrue(loserStats.getMatchTotal() == 1);
		assertTrue(loserStats.getMatchLost() == 1);
		assertTrue(loserStats.getPoints() == 0);		
	}
	
	
	
	private UserTO giveUser(Long id){
		UserTO user = new UserTO();
		user.setId(id);
		StatisticsTO userStatistics = new StatisticsTO();
		userStatistics.setId(id);
		user.setStatistics(userStatistics);
		return user;
	}
	

}
