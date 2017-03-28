package com.capgemini.service;

import java.util.List;

import com.capgemini.service.to.MatchTO;
import com.capgemini.service.to.UpdateTO;
import com.capgemini.service.to.UserTO;

public interface UserServicesFascade {
	
	public List<UserTO> showFullRanking();
	public List<UserTO> showPlayerLevelRanking(Long id, int currentLevel);
	public int getGeneralPlayerPosition(Long id);
	public int getPlayerPostionInItsLevel(Long id, int currentLevel);
	public UserTO getUserInformation(Long id);
	public UserTO updateUserData(UpdateTO user);
	public List<MatchTO> getUserMatchHistory(Long id);
	
}
