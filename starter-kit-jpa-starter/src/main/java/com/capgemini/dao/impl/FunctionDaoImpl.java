package com.capgemini.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.FunctionDao;
import com.capgemini.entity.FunctionEntity;
@Repository
public class FunctionDaoImpl extends AbstractDao<FunctionEntity, Long> implements FunctionDao{
	@PersistenceContext
	private EntityManager em;
}
