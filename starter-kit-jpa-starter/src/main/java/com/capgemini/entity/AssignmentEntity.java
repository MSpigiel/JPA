package com.capgemini.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ASSIGNMENT")
public class AssignmentEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(nullable = false)
	private Date start_date;
	@Column(nullable = true)
	private Date end_date;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id", nullable = false)
	EmployeeEntity employee;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="function_id", nullable = false)
	FunctionEntity function;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="project_id", nullable = false)
	ProjectEntity project;
	public AssignmentEntity() {
		
	}
	
	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}
	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}
	public FunctionEntity getFunction() {
		return function;
	}
	public void setFunction(FunctionEntity function) {
		this.function = function;
	}
	public ProjectEntity getProject() {
		return project;
	}
	public void setProject(ProjectEntity project) {
		this.project = project;
	}
	
	
}
