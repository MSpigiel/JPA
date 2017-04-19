package com.capgemini.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class ProjectEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(nullable = false, length = 45, unique = true)
	private String project_name;
	@Column(nullable = false)
	private boolean isInternal;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager", nullable = false)
	EmployeeEntity manager;

	public ProjectEntity() {
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public boolean isInternal() {
		return isInternal;
	}

	public void setInternal(boolean isInternal) {
		this.isInternal = isInternal;
	}

	public EmployeeEntity getManager() {
		return manager;
	}

	public void setManager(EmployeeEntity manager) {
		this.manager = manager;
	}

}
