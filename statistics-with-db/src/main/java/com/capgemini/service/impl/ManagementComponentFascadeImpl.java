package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.service.ManagementComponentFascade;
import com.capgemini.service.MatchHistoryService;
import com.capgemini.service.StatisticsService;
import com.capgemini.service.UserService;
import com.capgemini.service.to.MatchTO;
import com.capgemini.service.to.UserTO;

@Service
public class ManagementComponentFascadeImpl implements ManagementComponentFascade {

	@Autowired
	StatisticsService statsService;
	@Autowired
	MatchHistoryService matchService;
	@Autowired
	UserService userService;

	@Override
	public void manageFinishedMatch(Long winnerId, Long loserId) {

		UserTO winner = userService.readUser(winnerId);
		UserTO loser = userService.readUser(loserId);
		MatchTO match = new MatchTO(winner, loser);
		matchService.addMatchToHistory(match);
		statsService.updateStatistics(match.getWinner(), match.getLooser());

	}

}
