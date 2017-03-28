package com.capgemini.dataaccess.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dataaccess.dao.MatchDao;
import com.capgemini.dataaccess.entities.MatchEntity;

@Repository
public class MatchDaoImpl extends AbstractDao<MatchEntity, Long> implements MatchDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<MatchEntity> getUserMatchHistory(Long id) {
		return em.createQuery("Select m from MatchEntity m where m.winner.id = :id or m.looser.id = :id",
				MatchEntity.class).setParameter("id", id).getResultList();
	}
}
