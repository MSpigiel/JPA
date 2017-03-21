package com.capgemini.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.ProjectEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {

	@Autowired
	ProjectService projectService;
	@Autowired
	AssignmentService assignmentService;
	@PersistenceContext
	private EntityManager em;

	@Test
	@Transactional
	public void testShouldUpdateProjectData() {
		// given
		int modificationCounter = projectService.findAllProjects().get(0).getModificationCounter();
		ProjectEntity project = projectService.findAllProjects().get(0);
		project.setProject_name("new project name");
		projectService.updateProjectData(project);

		// when
		em.flush();
		int actualCounter = projectService.findAllProjects().get(0).getModificationCounter();

		// then
		assertTrue(modificationCounter + 1 == actualCounter);

	}

	@Test
	@Transactional
	public void testShouldAddNewProject() {
		// given
		int projectCount = projectService.findAllProjects().size();
		ProjectEntity project = projectService.addNewProject(true, "Neu projecto", 4L);
		
		// when
		int actualProjectCount = projectService.findAllProjects().size();
		
		//then
		assertTrue(projectCount+1 == actualProjectCount);
		assertNotNull(project.getId());

	}
	
	@Test
	@Transactional
	public void testDeleteNotActiveProject() {
		// given
		int projectCount = projectService.findAllProjects().size();
		ProjectEntity project = projectService.addNewProject(true, "Neu projecto", 4L);
		
		// when
		int actualProjectCount = projectService.findAllProjects().size();
		
		//then
		assertTrue(projectCount+1 == actualProjectCount);
		assertNotNull(project.getId());

	}
	
	@Test
	@Transactional
	public void testShouldDeleteProjectAndItsPastAssignments() {
		// given
		int projectCount = projectService.findAllProjects().size();
		int assignmentCount = assignmentService.findAllAssignments().size();
		// when
		projectService.deleteExistingProject(4L);
		
		int actualProjectCount = projectService.findAllProjects().size();
		int actualAssignmentCount = assignmentService.findAllAssignments().size();
		
		//then
		assertTrue(projectCount-1 == actualProjectCount);
		assertTrue(assignmentCount-1 == actualAssignmentCount);
		
	}
}
