package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.AssignmentEntity;
import com.capgemini.entity.EmployeeEntity;

public interface AssignmentService {

	public void deleteAllAssignmentsOfId(Long id);
	public void deleteAllAsignmentsOfProjectId(Long projectId);
	public void closeAssignment(Long employeeId, Long projectId);
	public AssignmentEntity createNewAssignment(Long employeeId, Long projectId, Long functionId);
	public List<EmployeeEntity> findAllActiveEmployeeOfProject(String projectName);
	public List<AssignmentEntity> findAllAssignments();
	public List<AssignmentEntity> findAllActiveAssignementsOfId(Long id);	
	public List<AssignmentEntity> findAllActiveAssignments();
	public List<AssignmentEntity> findAllPastAssignmentsOfId(Long id);
	public List<EmployeeEntity> findEmployeesWithProjectSenorityLongerThanX(Long projectId, int monthCount);
	

}
