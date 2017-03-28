package com.capgemini.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.BasePoints;
import com.capgemini.LevelRequirements;
import com.capgemini.dataaccess.dao.StatisticsDao;
import com.capgemini.dataaccess.entities.StatisticsEntity;
import com.capgemini.service.StatisticsService;
import com.capgemini.service.mapper.StatisticsMapper;
import com.capgemini.service.to.StatisticsTO;
import com.capgemini.service.to.UserTO;

@Service
public class StatisticsServiceImpl implements StatisticsService {

	@Autowired
	private StatisticsDao statsDao;

	@Override
	@Transactional(readOnly = false)
	public void updateStatistics(UserTO winner, UserTO loser) {
		HashMap<String, Long> pointsToBeAdded = countPoints(winner, loser);
		StatisticsTO winnerStatisticsTO = winner.getStatistics();
		winnerStatisticsTO = updateWinnerStatistics(winnerStatisticsTO, pointsToBeAdded.get("winnerPoints"));
		StatisticsEntity winnerStatistics = StatisticsMapper.map(winnerStatisticsTO);
		statsDao.update(winnerStatistics);

		StatisticsTO loserStatisticsTO = loser.getStatistics();
		loserStatisticsTO = updateLoserStatistics(loserStatisticsTO, pointsToBeAdded.get("loserPoints"));
		StatisticsEntity loserStatistics = StatisticsMapper.map(loserStatisticsTO);
		statsDao.update(loserStatistics);
	}

	private Double countProgressRatio(UserTO user) {
		int index = user.getStatistics().getCurrentLevel() - 1;
		Double progressPoints = Math.min((((double) user.getStatistics().getPoints()
				- (double) LevelRequirements.values()[index].getPointsRequired())
				/ ((double) (LevelRequirements.values()[index + 1].getPointsRequired()
						- LevelRequirements.values()[index].getPointsRequired()))),
				1.0);
		Double progressGames = Math.min((double) (user.getStatistics().getMatchTotal()
				- LevelRequirements.values()[index].getGamesPlayedRequired())
				/ ((double) (LevelRequirements.values()[index + 1].getGamesPlayedRequired()
						- LevelRequirements.values()[index].getGamesPlayedRequired())),
				1.0);
		Double progressWins = Math.min(
				(double) ((double) user.getStatistics().getMatchWon() / (double) user.getStatistics().getMatchTotal()
						- LevelRequirements.values()[index].getPercentageWinsRequired())
						/ ((double) (LevelRequirements.values()[index + 1].getPercentageWinsRequired()
								- LevelRequirements.values()[index].getPercentageWinsRequired())),
				1.0);

		return (progressPoints + progressGames + progressWins) / 3;
	}

	private HashMap<String, Long> countPoints(UserTO winner, UserTO loser) {
		HashMap<String, Long> resultMap = new HashMap<String, Long>();
		int index = (loser.getStatistics().getCurrentLevel()) - winner.getStatistics().getCurrentLevel() + 9;

		Long baseProfit = (long) (BasePoints.values()[index].getProfitBase()
				* winner.getStatistics().getCurrentLevel());
		Long baseLoss = (long) (BasePoints.values()[index].getLossBase() * loser.getStatistics().getCurrentLevel());
		Double winnerProgress = 0.0;
		Double loserProgress = 0.0;
		if (winner.getStatistics().getCurrentLevel() != 10) {
			winnerProgress = countProgressRatio(winner);
		}
		if (loser.getStatistics().getCurrentLevel() != 10) {
			loserProgress = countProgressRatio(loser);
		}
		Long winnerBonus = (long) (Math.floor((loserProgress - winnerProgress) * baseProfit * 0.5));
		Long loserBonus = (long) (Math.floor((winnerProgress - loserProgress) * baseProfit * 0.5));
		Long winnerProfit = baseProfit + winnerBonus;
		Long loserLoss = baseLoss - loserBonus;
		resultMap.put("winnerPoints", winnerProfit);
		resultMap.put("loserPoints", loserLoss);
		return resultMap;
	}

	private StatisticsTO updateWinnerStatistics(StatisticsTO statisticsTO, Long points) {
		StatisticsTO resultTO = statisticsTO;
		resultTO.setMatchTotal(resultTO.getMatchTotal() + 1);
		resultTO.setMatchWon(resultTO.getMatchWon() + 1);
		resultTO.setPoints(resultTO.getPoints() + points);
		return resultTO;
	}

	private StatisticsTO updateLoserStatistics(StatisticsTO statisticsTO, Long points) {
		StatisticsTO resultTO = statisticsTO;
		resultTO.setMatchTotal(resultTO.getMatchTotal() + 1);
		resultTO.setMatchLost(resultTO.getMatchLost() + 1);
		if (resultTO.getPoints() - points < 0) {
			resultTO.setPoints(0L);
		} else {
			resultTO.setPoints(resultTO.getPoints() - points);
		}
		return resultTO;
	}
}