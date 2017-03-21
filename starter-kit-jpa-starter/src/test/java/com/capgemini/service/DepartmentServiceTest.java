package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.entity.DepartmentEntity;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentServiceTest {

	@Autowired
	private DepartmentService departmentService;
	@PersistenceContext
	private EntityManager em;
	
    @Test
    @Transactional
    public void testIfDepartmentIsUpdated() {
    	// given
    	DepartmentEntity department = departmentService.findDepartmentById(1L);
    	int updatesCounter = department.getModificationCounter();
    	// when
    	department.setDepartment_name("New department name");
    	department.setEmail("new@email.com");
    	DepartmentEntity updatedDepartment = departmentService.updateDepartment(department);
    	em.flush();
    	// then
    	assertNotNull(updatedDepartment);
    	assertEquals(updatesCounter+1, updatedDepartment.getModificationCounter());
    }
    
    @Test
    @Transactional
    public void testShouldSaveNewDepartment() {
    	// given
    	int countBefore = departmentService.findAllDepartments().size();
    	System.out.println(countBefore);
    	// when    	
    	DepartmentEntity savedDepartment = departmentService.createDepartment("nowy1","nowy1@nowy.pl","123155123","321325521");
    	// then
    	assertNotNull(savedDepartment.getId());
    	assertEquals(countBefore+1, departmentService.findAllDepartments().size());
    }

}
