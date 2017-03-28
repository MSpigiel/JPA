package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dataaccess.dao.UserDao;
import com.capgemini.service.RankingService;
import com.capgemini.service.mapper.UserMapper;
import com.capgemini.service.to.UserTO;

@Service
public class RankingServiceImpl implements RankingService {

	@Autowired
	UserDao userDao;

	@Override
	public Integer getPlayerPostion(Long id) {

		List<UserTO> ranking = UserMapper.map2TOs(userDao.generateFullRanking());
		int playerPosition = 0;

		for (int i = 0; i < ranking.size(); i++) {
			playerPosition++;
			if (ranking.get(i).getId() == id) {
				return playerPosition;
			}
		}

		return playerPosition;
	}

	@Override
	public Integer getPlayerPostionAtPlayerLevel(Long id, int currentLevel) {

		List<UserTO> playerLevelRanking = UserMapper.map2TOs(userDao.generateRankingOnUserLevel(currentLevel));
		int playerPosition = 0;

		for (int i = 0; i < playerLevelRanking.size(); i++) {
			playerPosition++;
			if (playerLevelRanking.get(i).getId() == id) {
				return playerPosition;
			}
		}

		return playerPosition;
	}

	@Override
	public List<UserTO> getFullRanking() {
		return UserMapper.map2TOs(userDao.generateFullRanking());
	}

	@Override
	public List<UserTO> getRankingAtPlayerLevel(int currentLevel) {
		return UserMapper.map2TOs(userDao.generateRankingOnUserLevel(currentLevel));
	}

}
