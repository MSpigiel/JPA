package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.ProjectEntity;

public interface ProjectService {

	public ProjectEntity addNewProject(boolean isInternal, String projectName, Long managerId);
	public void deleteExistingProject(Long id);
	public ProjectEntity updateProjectData(ProjectEntity project);
	public List<ProjectEntity> findAllProjects();
	public ProjectEntity findProjectById(Long id);
	
}
