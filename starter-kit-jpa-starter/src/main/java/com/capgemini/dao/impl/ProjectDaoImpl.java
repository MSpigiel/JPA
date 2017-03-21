package com.capgemini.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.ProjectDao;
import com.capgemini.entity.ProjectEntity;
@Repository
public class ProjectDaoImpl extends AbstractDao<ProjectEntity, Long> implements ProjectDao {

	@PersistenceContext
	private EntityManager em;

	
	
}
