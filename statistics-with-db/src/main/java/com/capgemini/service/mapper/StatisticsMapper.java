package com.capgemini.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.dataaccess.entities.StatisticsEntity;
import com.capgemini.service.to.StatisticsTO;

public class StatisticsMapper {
	public static StatisticsTO map(StatisticsEntity statisticsEntity) {
		if (statisticsEntity != null) {
			StatisticsTO StatisticsTO = new StatisticsTO();
			StatisticsTO.setId(statisticsEntity.getId());
			StatisticsTO.setMatchDraw(statisticsEntity.getMatchDraw());
			StatisticsTO.setMatchWon(statisticsEntity.getMatchWon());
			StatisticsTO.setMatchLost(statisticsEntity.getMatchLost());
			StatisticsTO.setMatchTotal(statisticsEntity.getMatchTotal());
			StatisticsTO.setCurrentLevel(statisticsEntity.getCurrentLevel());
			StatisticsTO.setPoints(statisticsEntity.getPoints());
			StatisticsTO.setLevelName(statisticsEntity.getLevelName());
			return StatisticsTO;
		}
		return null;
	}

	public static StatisticsEntity map(StatisticsTO StatisticsTO) {
		if (StatisticsTO != null) {
			StatisticsEntity statisticsEntity = new StatisticsEntity();
			statisticsEntity.setId(StatisticsTO.getId());
			statisticsEntity.setMatchDraw(StatisticsTO.getMatchDraw());
			statisticsEntity.setMatchWon(StatisticsTO.getMatchWon());
			statisticsEntity.setMatchLost(StatisticsTO.getMatchLost());
			statisticsEntity.setMatchTotal(StatisticsTO.getMatchTotal());
			statisticsEntity.setCurrentLevel(StatisticsTO.getCurrentLevel());
			statisticsEntity.setPoints(StatisticsTO.getPoints());
			statisticsEntity.setLevelName(StatisticsTO.getLevelName());
			return statisticsEntity;
		}
		return null;
	}

	public static List<StatisticsTO> map2TOs(List<StatisticsEntity> statisticsEntities) {
		return statisticsEntities.stream().map(StatisticsMapper::map).collect(Collectors.toList());
	}

	public static List<StatisticsEntity> map2Entities(List<StatisticsTO> StatisticsTOs) {
		return StatisticsTOs.stream().map(StatisticsMapper::map).collect(Collectors.toList());
	}
}
