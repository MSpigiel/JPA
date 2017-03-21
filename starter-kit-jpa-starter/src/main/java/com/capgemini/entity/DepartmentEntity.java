package com.capgemini.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class DepartmentEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(nullable = false, length = 45, unique = true)
	private String department_name;
	@Column(nullable = false, length = 45, unique = true)
	private String email;
	@Column(nullable = false, length = 9, unique = true)
	private String homeNumber;
	@Column(nullable = false, length = 9, unique = true)
	private String mobileNumber;

	
	public DepartmentEntity(){
		
	}

	public Long getDepartment_id() {
		return getId();
	}

	public void setDepartment_id(Long department_id) {
		setId(department_id);
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



}
