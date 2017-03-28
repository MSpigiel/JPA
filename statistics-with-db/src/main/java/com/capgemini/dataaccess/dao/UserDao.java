package com.capgemini.dataaccess.dao;

import java.util.List;

import com.capgemini.dataaccess.entities.UserEntity;

public interface UserDao  extends Dao<UserEntity, Long> {

	public UserEntity findByEmail(String email);

	public List<UserEntity> generateFullRanking();

	public List<UserEntity> generateRankingOnUserLevel(int level);
	
}
