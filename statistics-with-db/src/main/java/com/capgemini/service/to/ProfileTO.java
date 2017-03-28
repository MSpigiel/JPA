package com.capgemini.service.to;

public class ProfileTO {
	
	private Long id;
	private String name;
	private String surname;
	private String aboutMe;
	private String lifeMotto;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public ProfileTO(){
		this.name = "";
		this.surname = "";
		this.aboutMe = "";
		this.lifeMotto = "";
	}
	
}
