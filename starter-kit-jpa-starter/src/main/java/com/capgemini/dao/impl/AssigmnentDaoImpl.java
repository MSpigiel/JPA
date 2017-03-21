package com.capgemini.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.capgemini.dao.AssignmentDao;
import com.capgemini.entity.AssignmentEntity;
import com.capgemini.entity.EmployeeEntity;
@Repository
public class AssigmnentDaoImpl extends AbstractDao<AssignmentEntity, Long> implements AssignmentDao{

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<EmployeeEntity> findActiveProjectMembers(String projectName) {
		return em.createQuery("Select a.employee from AssignmentEntity a where a.project.project_name = :projectName and a.end_date is null", EmployeeEntity.class)
				.setParameter("projectName", projectName)
				.getResultList();
	}

	@Override
	public List<EmployeeEntity> findPastProjectMemebrs(String projectName, int monthCount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AssignmentEntity> findUserActiveProjects(Long id) {
		return em.createQuery("Select a from AssignmentEntity a where a.employee.id = :id and a.end_date is null", AssignmentEntity.class)
				.setParameter("id", id)
				.getResultList();
	}

	@Override
	public void deleteAllAssignmentsOfId(Long id) {
		em.createQuery("Delete FROM AssignmentEntity a WHERE a.employee.id = :id")
		.setParameter("id", id)
		.executeUpdate();		
	}

	@Override
	public List<AssignmentEntity> findAllPastUserProjects(Long id) {
		return em.createQuery("Select a from AssignmentEntity a where a.employee.id = :id", AssignmentEntity.class)
				.setParameter("id", id)
				.getResultList();
	}

	@Override
	public List<AssignmentEntity> findSpecifiedAssignment(Long employeeId, Long projectId) {
		return em.createQuery("Select a from AssignmentEntity a where a.employee.id = :employeeId and a.project.id = :projectId and a.end_date is null", AssignmentEntity.class)
				.setParameter("employeeId", employeeId)
				.setParameter("projectId", projectId)
				.getResultList();
	}

	@Override
	public List<EmployeeEntity> findActiveProjectMembersById(Long projectId) {
		return em.createQuery("Select a.employee from AssignmentEntity a where a.project.id = :projectId and a.end_date is null", EmployeeEntity.class)
				.setParameter("projectId", projectId)
				.getResultList();
	}

	@Override
	public List<AssignmentEntity> findAllActiveProjects() {
		return em.createQuery("Select a from AssignmentEntity a where a.end_date is null", AssignmentEntity.class)
				.getResultList();
	}

	@Override
	public void deleteAllAssignmentsOfProjectId(Long projectId) {
		em.createQuery("Delete FROM AssignmentEntity a WHERE a.project.id = :projectId")
		.setParameter("projectId", projectId)
		.executeUpdate();		
	}

	@Override
	public List<AssignmentEntity> findAllProjectAssignments(Long projectId) {
		return em.createQuery("Select a from AssignmentEntity a where a.project.id = :projectId", AssignmentEntity.class)
				.setParameter("projectId", projectId)
				.getResultList();
	}
	
	
}
