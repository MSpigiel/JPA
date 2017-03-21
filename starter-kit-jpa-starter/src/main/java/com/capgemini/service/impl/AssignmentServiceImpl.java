package com.capgemini.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.AssignmentDao;
import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.FunctionDao;
import com.capgemini.dao.ProjectDao;
import com.capgemini.entity.AssignmentEntity;
import com.capgemini.entity.EmployeeEntity;
import com.capgemini.entity.FunctionEntity;
import com.capgemini.entity.ProjectEntity;
import com.capgemini.service.AssignmentService;
import com.capgemini.service.AssignmentValidationService;

@Service
@Transactional(readOnly = true)
public class AssignmentServiceImpl implements AssignmentService {

	@Autowired
	AssignmentDao assignmentDao;

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	FunctionDao functionDao;

	@Autowired
	ProjectDao projectDao;

	@Autowired
	AssignmentValidationService validationService;

	@Override
	@Transactional(readOnly = false)
	public void deleteAllAssignmentsOfId(Long id) {
		assignmentDao.deleteAllAssignmentsOfId(id);
	}

	@Override
	@Transactional(readOnly = false)
	public AssignmentEntity createNewAssignment(Long employeeId, Long projectId, Long functionId) {
		if (validationService.checkIfAssignmentExists(employeeId, projectId)) {
			return null;
		}
		return assignmentDao.save(buildAssignment(employeeId, projectId, functionId));
	}

	@Override
	public List<EmployeeEntity> findAllActiveEmployeeOfProject(String projectName) {
		return assignmentDao.findActiveProjectMembers(projectName);
	}

	@Override
	public List<AssignmentEntity> findAllAssignments() {
		return assignmentDao.findAll();
	}

	@Override
	public List<AssignmentEntity> findAllActiveAssignementsOfId(Long id) {
		return assignmentDao.findUserActiveProjects(id);
	}

	@Override
	public void closeAssignment(Long employeeId, Long projectId) {
		AssignmentEntity assignment = assignmentDao.findSpecifiedAssignment(employeeId, projectId).get(0);
		if (validationService.checkIfAssignmentCanBeClosed(assignment)) {
			return;
		} else
			assignment.setEnd_date(new Date());
		assignmentDao.update(assignment);
	}

	@Override
	public List<AssignmentEntity> findAllActiveAssignments() {
		return assignmentDao.findAllActiveProjects();
	}

	@Override
	public List<AssignmentEntity> findAllPastAssignmentsOfId(Long id) {
		return assignmentDao.findAllPastUserProjects(id);
	}

	@Override
	public List<EmployeeEntity> findEmployeesWithProjectSenorityLongerThanX(Long projectId, int monthCount) {
		List<AssignmentEntity> assignments = assignmentDao.findAllProjectAssignments(projectId);
		HashMap<Long, Long> employeesWorkTime = new HashMap<Long, Long>();
		List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
		Calendar cal = Calendar.getInstance();
		int months = 0;

		for (AssignmentEntity assignment : assignments) {
			employeesWorkTime.put(assignment.getEmployee().getId(), 0L);
		}

		for (AssignmentEntity assignment : assignments) {
			employeesWorkTime.put(assignment.getEmployee().getId(),
					employeesWorkTime.get(assignment.getEmployee().getId()) + calculateTime(assignment));
		}

		for (Map.Entry<Long, Long> entry : employeesWorkTime.entrySet()) {
			cal.setTimeInMillis((Long)entry.getValue());
			months = (cal.get(Calendar.YEAR)-1970) * 12 + cal.get(Calendar.MONTH);

			if (months >= monthCount) {
				EmployeeEntity employee = employeeDao.findOne(entry.getKey());
				employees.add(employee);
			}
		}
		return employees;
	}

	@Override
	public void deleteAllAsignmentsOfProjectId(Long projectId) {
		assignmentDao.deleteAllAssignmentsOfProjectId(projectId);
	}

	private AssignmentEntity buildAssignment(Long employeeId, Long projectId, Long functionId) {
		AssignmentEntity newAssignment = new AssignmentEntity();
		EmployeeEntity employeeEntity = employeeDao.findOne(employeeId);
		ProjectEntity projectEntity = projectDao.findOne(projectId);
		FunctionEntity functionEntity = functionDao.findOne(functionId);
		newAssignment.setEmployee(employeeEntity);
		newAssignment.setFunction(functionEntity);
		newAssignment.setProject(projectEntity);
		newAssignment.setStart_date(new Date());
		return newAssignment;
	}

	private Long calculateTime(AssignmentEntity assignment) {
		if (assignment.getEnd_date() == null || new Date().getTime() < assignment.getEnd_date().getTime()) {
			return new Date().getTime() - assignment.getStart_date().getTime();
		} else
			return assignment.getEnd_date().getTime() - assignment.getStart_date().getTime();
	}

}
