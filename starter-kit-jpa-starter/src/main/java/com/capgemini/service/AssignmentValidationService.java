package com.capgemini.service;

import com.capgemini.entity.AssignmentEntity;

public interface AssignmentValidationService {

	public boolean checkIfAssignmentExists(Long employeeId, Long projectId);
	public boolean checkIfProjectIsActive(Long projectId);
	public boolean checkIfAssignmentCanBeClosed(AssignmentEntity assignment);

}
