package com.capgemini.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.EmployeeDao;
import com.capgemini.dao.ProjectDao;
import com.capgemini.entity.ProjectEntity;
import com.capgemini.service.AssignmentService;
import com.capgemini.service.AssignmentValidationService;
import com.capgemini.service.ProjectService;
@Service
@Transactional(readOnly=true)
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDao projectDao;
	@Autowired
	EmployeeDao employeeDao;
	@Autowired
	AssignmentService assignmentService;
	@Autowired
	AssignmentValidationService validationService;
	
	@Override
	@Transactional(readOnly = false)
	public ProjectEntity addNewProject(boolean isInternal, String projectName, Long managerId) {
		return projectDao.save(buildProject(isInternal, projectName, managerId));
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteExistingProject(Long id) {
		if(validationService.checkIfProjectIsActive(id)){
			return;
		} else
		projectDao.delete(id);
		assignmentService.deleteAllAsignmentsOfProjectId(id);		
	}

	@Override
	@Transactional(readOnly = false)
	public ProjectEntity updateProjectData(ProjectEntity project) {
		return projectDao.update(project);
	}

	@Override
	public List<ProjectEntity> findAllProjects() {
		return projectDao.findAll();
	}

	@Override
	public ProjectEntity findProjectById(Long id) {
		return projectDao.findOne(id);
	}
	
	private ProjectEntity buildProject(boolean isInternal, String projectName, Long managerId){
		ProjectEntity newProject = new ProjectEntity();
		newProject.setInternal(isInternal);
		newProject.setProject_name(projectName);
		newProject.setManager(employeeDao.findOne(managerId));
		return newProject;
	}

}
