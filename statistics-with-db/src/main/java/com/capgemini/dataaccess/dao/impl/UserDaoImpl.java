package com.capgemini.dataaccess.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dataaccess.dao.UserDao;
import com.capgemini.dataaccess.entities.UserEntity;

@Repository
public class UserDaoImpl extends AbstractDao<UserEntity, Long> implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public UserEntity findByEmail(String email) {
		return em.createQuery("Select u from UserEntity u where u.email = :email", UserEntity.class)
				.setParameter("email", email)
				.getSingleResult();
	}

	@Override
	public List<UserEntity> generateFullRanking() {
		return em.createQuery("Select u from UserEntity u ORDER BY u.statistics.points DESC", UserEntity.class)
				.getResultList();
	}

	@Override
	public List<UserEntity> generateRankingOnUserLevel(int level) {
		return em.createQuery(
				"Select u from UserEntity u WHERE u.statistics.currentLevel = :level ORDER BY u.statistics.points DESC",
				UserEntity.class)
				.setParameter("level", level)
				.getResultList();
	}
}
