package com.capgemini.dataaccess.dao;

import java.util.List;

import com.capgemini.dataaccess.entities.MatchEntity;

public interface MatchDao extends Dao<MatchEntity, Long> {

	public List<MatchEntity> getUserMatchHistory(Long id);
	
}
