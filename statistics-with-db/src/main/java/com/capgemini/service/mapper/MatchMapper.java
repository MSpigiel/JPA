package com.capgemini.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.dataaccess.entities.MatchEntity;
import com.capgemini.service.to.MatchTO;

public class MatchMapper {
	public static MatchTO map(MatchEntity matchEntity) {
		if (matchEntity != null) {
			MatchTO matchTO = new MatchTO();
			matchTO.setGameDate(matchEntity.getGameDate());
			matchTO.setWinner(UserMapper.map(matchEntity.getWinner()));
			matchTO.setLooser(UserMapper.map(matchEntity.getLooser()));
			return matchTO;
		}
		return null;
	}

	public static MatchEntity map(MatchTO matchTO) {
		if (matchTO != null) {
			MatchEntity matchEntity = new MatchEntity();
			matchEntity.setGameDate(matchTO.getGameDate());
			matchEntity.setWinner(UserMapper.map(matchTO.getWinner()));
			matchEntity.setLooser(UserMapper.map(matchTO.getLooser()));
			return matchEntity;
		}
		return null;
	}

	public static List<MatchTO> map2TOs(List<MatchEntity> matchEntities) {
		return matchEntities.stream().map(MatchMapper::map).collect(Collectors.toList());
	}

	public static List<MatchEntity> map2Entities(List<MatchTO> matchTOs) {
		return matchTOs.stream().map(MatchMapper::map).collect(Collectors.toList());
	}
}
