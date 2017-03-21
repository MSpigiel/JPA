package com.capgemini.dao.impl;

import java.util.List;

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
	public List<EmployeeEntity> findEmployeeByName(String name) {
		return em.createQuery("Select e from EmployeeEntity e where e.employee_name = :name", EmployeeEntity.class)
				.setParameter("name", name)
				.getResultList();
	}	

	@Override
	public List<EmployeeEntity> findEmployeeByLastName(String lastName) {
		return em.createQuery("Select e from EmployeeEntity e where e.employee_lastName = :lastName", EmployeeEntity.class)
				.setParameter("lastName", lastName)
				.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeeByNameAndLastName(String name, String lastName) {
		return em.createQuery("Select e from EmployeeEntity e where e.employee_lastName = :lastName and e.employee_name = :name", EmployeeEntity.class)
				.setParameter("lastName", lastName)
				.setParameter("name", name)
				.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeeByDepartmentId(Long id) {
		return em.createQuery("Select e from EmployeeEntity e where e.department.id = :id ", EmployeeEntity.class)
				.setParameter("id", id)
				.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeeByDepartmentName(String name) {
		return em.createQuery("Select e from EmployeeEntity e where e.department.department_name = :name ", EmployeeEntity.class)
				.setParameter("name", name)
				.getResultList();
	}

	@Override
	public List<EmployeeEntity> findEmployeeBySocialSecurity(String socialSecurity) {
		return em.createQuery("Select e from EmployeeEntity e where e.socialSecurity = :socialSecurity", EmployeeEntity.class)
				.setParameter("socialSecurity", socialSecurity)
				.getResultList();
	}

	@Override
	public EmployeeEntity findEmployeeByEmail(String email) {
		return em.createQuery("Select e from EmployeeEntity e where e.email = :email", EmployeeEntity.class)
				.setParameter("email", email)
				.getSingleResult();
	}

}
