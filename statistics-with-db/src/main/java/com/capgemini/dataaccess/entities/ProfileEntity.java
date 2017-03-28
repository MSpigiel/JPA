package com.capgemini.dataaccess.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PROFILE")
public class ProfileEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = true, length = 45)
	private String name;
	
	@Column(nullable = true, length = 45)
	private String surname;
	
	@Column(nullable = true, length = 45)
	private String aboutMe;
	
	@Column(nullable = true, length = 45)
	private String lifeMotto;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getLifeMotto() {
		return lifeMotto;
	}

	public void setLifeMotto(String lifeMotto) {
		this.lifeMotto = lifeMotto;
	}

	public ProfileEntity() {
		this.name = "";
		this.surname = "";
		this.aboutMe = "";
		this.lifeMotto = "";
	}

}
