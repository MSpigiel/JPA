package com.capgemini.dataaccess.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dataaccess.dao.ProfileDao;
import com.capgemini.dataaccess.entities.ProfileEntity;

@Repository
public class ProfileDaoImpl extends AbstractDao<ProfileEntity, Long> implements ProfileDao {
	@PersistenceContext
	private EntityManager em;
}
