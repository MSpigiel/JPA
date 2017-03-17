package com.capgemini.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


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
	
//	@Test
//    public void testShouldFindDepartmentById() {
//        // given
//        final long DepartmentId = 1;
//        // when
//        DepartmentEntity DepartmentEntity = mSUT.findDepartmentById(DepartmentId);
//        // then
//        assertNotNull(DepartmentEntity);
//        assertEquals("Pierwsza książka", DepartmentEntity.getTitle());
//    }
//
//    @Test
//    public void testShouldFindDepartmentsByTitle() {
//        // given
//        final String DepartmentTitle = "p";
//        // when
//        List<DepartmentEntity> DepartmentsEntity = mSUT.findDepartmentsByTitle(DepartmentTitle);
//        // then
//        assertNotNull(DepartmentsEntity);
//        assertFalse(DepartmentsEntity.isEmpty());
//        assertEquals("Pierwsza książka", DepartmentsEntity.get(0).getTitle());
//    }
//    
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
    	// then
    	assertNotNull(updatedDepartment);
    	assertEquals(updatesCounter+1, updatedDepartment.getModificationCounter());
    }
    
    @Test
    @Transactional
    public void testShouldSaveDepartment() {
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
