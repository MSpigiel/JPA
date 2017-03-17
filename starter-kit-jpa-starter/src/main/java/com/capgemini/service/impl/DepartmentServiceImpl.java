package com.capgemini.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dao.DepartmentDao;
import com.capgemini.entity.DepartmentEntity;
import com.capgemini.service.DepartmentService;
@Service
@Transactional(readOnly=true)
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public DepartmentEntity findDepartmentByName(String name) {
		return departmentDao.findDepartmentByName(name);
	}

	@Override
	public DepartmentEntity findDepartmentById(Long id) {
		return departmentDao.findOne(id);
	}

	@Override
	@Transactional(readOnly=false)
	public DepartmentEntity createDepartment(String name, String email, String homeNumber, String mobileNumber) {
		DepartmentEntity department = new DepartmentEntity();
		department.setDepartment_name(name);
		department.setEmail(email);
		department.setHomeNumber(homeNumber);
		department.setMobileNumber(mobileNumber);
		return departmentDao.save(department);
	}

	@Override
	public List<DepartmentEntity> findAllDepartments() {
		return departmentDao.findAll();
	}

	@Override
	@Transactional(readOnly=false)
	public DepartmentEntity updateDepartment(DepartmentEntity department) {
		return departmentDao.update(department);
	}

}
