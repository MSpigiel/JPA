package com.capgemini.service;

import java.util.List;

import com.capgemini.service.to.UserTO;

public interface RankingService {
	
	public Integer getPlayerPostion(Long id);
	public Integer getPlayerPostionAtPlayerLevel(Long id, int currentLevel);
	public List<UserTO> getFullRanking();
	public List<UserTO> getRankingAtPlayerLevel(int currentLevel);
}
