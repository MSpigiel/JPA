package com.capgemini.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.DepartmentDao;
import com.capgemini.entity.DepartmentEntity;
@Repository
public class DepartmentDaoImpl extends AbstractDao<DepartmentEntity, Long> implements DepartmentDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public DepartmentEntity findDepartmentByName(String name) {
		return em.createQuery("Select d from DepartmentEntity d where d.department_name = :name", DepartmentEntity.class)
				.setParameter("name", name)
				.getSingleResult();
	}	

}
