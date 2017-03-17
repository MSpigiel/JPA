package com.capgemini.dao;

import com.capgemini.entity.DepartmentEntity;

public interface DepartmentDao extends Dao<DepartmentEntity, Long>{
	
	DepartmentEntity findDepartmentByName(String name);
}
