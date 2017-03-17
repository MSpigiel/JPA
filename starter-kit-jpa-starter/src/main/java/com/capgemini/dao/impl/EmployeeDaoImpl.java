package com.capgemini.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.entity.EmployeeEntity;
@Repository
public class EmployeeDaoImpl extends AbstractDao<EmployeeEntity, Long> implements EmployeeDao{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public EmployeeEntity findEmployeeByName(String name) {
		return em.createQuery("Select e from EmployeeEntity e where e.employee_name = :name", EmployeeEntity.class)
				.setParameter("name", name)
				.getSingleResult();
	}	

	@Override
	public EmployeeEntity findEmployeeByLastName(String lastName) {
		return em.createQuery("Select e from EmployeeEntity e where e.employee_lastName = :lastName", EmployeeEntity.class)
				.setParameter("lastName", lastName)
				.getSingleResult();
	}

	@Override
	public EmployeeEntity findEmployeeByNameAndLastName(String name, String lastName) {
		return em.createQuery("Select e from EmployeeEntity e where e.employee_lastName = :lastName and e.employee_name = :name", EmployeeEntity.class)
				.setParameter("lastName", lastName)
				.setParameter("name", name)
				.getSingleResult();
	}

}
