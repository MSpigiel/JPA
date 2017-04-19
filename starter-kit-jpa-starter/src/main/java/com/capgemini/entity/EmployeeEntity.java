package com.capgemini.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(nullable = false, length = 45)
	private String employee_name;
	@Column(nullable = false, length = 45)
	private String employee_lastName;
	@Column(nullable = false, length = 11, unique = true)
	private String socialSecurity;
	@Column(nullable = true)
	private Date birthDate;
	@Column(nullable = true)
	private String homeNumber;
	@Column(nullable = false)
	private String mobileNumber;
	@Column(nullable = false, unique = true)
	private String email;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	DepartmentEntity department;
	@OneToMany(mappedBy = "employee")
	List<AssignmentEntity> assignments;

	public EmployeeEntity() {

	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_lastName() {
		return employee_lastName;
	}

	public void setEmployee_lastName(String employee_lastName) {
		this.employee_lastName = employee_lastName;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity department) {
		this.department = department;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
