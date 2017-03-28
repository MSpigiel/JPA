package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.service.MatchHistoryService;
import com.capgemini.service.RankingService;
import com.capgemini.service.UserService;
import com.capgemini.service.UserServicesFascade;
import com.capgemini.service.to.MatchTO;
import com.capgemini.service.to.UpdateTO;
import com.capgemini.service.to.UserTO;

@Service
public class UserServicesFascadeImpl implements UserServicesFascade {

	@Autowired
	RankingService rankingService;
	@Autowired
	UserService userService;
	@Autowired
	MatchHistoryService matchService;

	@Override
	public List<UserTO> showFullRanking() {
		return rankingService.getFullRanking();
	}

	@Override
	public List<UserTO> showPlayerLevelRanking(Long id, int currentLevel) {
		return rankingService.getRankingAtPlayerLevel(currentLevel);
	}

	@Override
	public int getGeneralPlayerPosition(Long id) {
		return rankingService.getPlayerPostion(id);
	}

	@Override
	public int getPlayerPostionInItsLevel(Long id, int currentLevel) {
		return rankingService.getPlayerPostionAtPlayerLevel(id, currentLevel);
	}

	@Override
	public UserTO getUserInformation(Long id) {
		return userService.readUser(id);
	}

	@Override
	public UserTO updateUserData(UpdateTO data) {
		UserTO user = userService.readUser(data.getId());
		if (data.getAboutMe() != null)
			user.getProfile().setAboutMe(data.getAboutMe());
		if (data.getLifeMotto() != null)
			user.getProfile().setLifeMotto(data.getLifeMotto());
		if (data.getName() != null)
			user.getProfile().setName(data.getName());
		if (data.getSurname() != null)
			user.getProfile().setSurname(data.getSurname());
		if (data.getEmail() != null)
			user.setEmail(data.getEmail());
		if (data.getLogin() != null)
			user.setLogin(data.getLogin());

		return userService.updateUser(user);
	}

	@Override
	public List<MatchTO> getUserMatchHistory(Long id) {
		return matchService.getUserMatchHistory(id);
	}

}
