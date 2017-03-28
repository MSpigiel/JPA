package com.capgemini.dataaccess.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dataaccess.dao.StatisticsDao;
import com.capgemini.dataaccess.entities.StatisticsEntity;
@Repository
public class StatisticsDaoImpl extends AbstractDao<StatisticsEntity, Long> implements StatisticsDao {
	@PersistenceContext
	private EntityManager em;
}
