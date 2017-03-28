package com.capgemini.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.dataaccess.entities.ProfileEntity;
import com.capgemini.dataaccess.entities.StatisticsEntity;
import com.capgemini.dataaccess.entities.UserEntity;
import com.capgemini.service.to.ProfileTO;
import com.capgemini.service.to.StatisticsTO;
import com.capgemini.service.to.UserTO;

public class UserMapper {
	
	public static UserTO map(UserEntity userEntity) {
		if (userEntity != null) {
			UserTO userTO = new UserTO();
			ProfileTO profileTO = new ProfileTO();
			StatisticsTO statisticsTO = new StatisticsTO();
			profileTO = ProfileMapper.map(userEntity.getProfile());
			statisticsTO = StatisticsMapper.map(userEntity.getStatistics());
			userTO.setProfile(profileTO);
			userTO.setStatistics(statisticsTO);
			userTO.setEmail(userEntity.getEmail());
			userTO.setId(userEntity.getId());
			userTO.setLogin(userEntity.getLogin());
			userTO.setPassword(userEntity.getPassword());
			return userTO;
		}
		return null;
	}

	public static UserEntity map(UserTO userTO) {
		if (userTO != null) {
			UserEntity userEntity = new UserEntity();
			ProfileEntity profileEntity = new ProfileEntity();
			StatisticsEntity statisticsEntity = new StatisticsEntity();
			profileEntity = ProfileMapper.map(userTO.getProfile());
			statisticsEntity = StatisticsMapper.map(userTO.getStatistics());
			userEntity.setProfile(profileEntity);
			userEntity.setStatistics(statisticsEntity);
			userEntity.setEmail(userTO.getEmail());
			userEntity.setId(userTO.getId());
			userEntity.setLogin(userTO.getLogin());
			userEntity.setPassword(userTO.getPassword());
			return userEntity;
		}
		return null;
	}
	
	public static List<UserTO> map2TOs(List<UserEntity> userEntities) {
		return userEntities.stream().map(UserMapper::map).collect(Collectors.toList());
	}

	public static List<UserEntity> map2Entities(List<UserTO> userTOs) {
		return userTOs.stream().map(UserMapper::map).collect(Collectors.toList());
	}
}
