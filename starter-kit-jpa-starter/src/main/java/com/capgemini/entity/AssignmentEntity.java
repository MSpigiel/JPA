package com.capgemini.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

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
	private LocalDateTime start_date;
	@Column(nullable = true)
	private LocalDateTime end_date;
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
	public LocalDateTime getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}
	public LocalDateTime getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDateTime end_date) {
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
