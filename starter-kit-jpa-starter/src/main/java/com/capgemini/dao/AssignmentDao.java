package com.capgemini.dao;

import java.util.List;

import com.capgemini.entity.AssignmentEntity;
import com.capgemini.entity.EmployeeEntity;

public interface AssignmentDao extends Dao<AssignmentEntity, Long> {

	public List<EmployeeEntity> findActiveProjectMembers(String projectName);
	public List<EmployeeEntity> findActiveProjectMembersById(Long projectId);
	public List<EmployeeEntity> findPastProjectMemebrs(String projectName, int monthCount);
	public List<AssignmentEntity> findUserActiveProjects(Long id);
	public void deleteAllAssignmentsOfId(Long id);
	public void deleteAllAssignmentsOfProjectId(Long projectId);
	public List<AssignmentEntity> findAllPastUserProjects(Long id);
	public List<AssignmentEntity> findSpecifiedAssignment(Long employeeId, Long projectId);
	public List<AssignmentEntity> findAllActiveProjects();
	public List<AssignmentEntity> findAllProjectAssignments(Long projectId);
	
}
