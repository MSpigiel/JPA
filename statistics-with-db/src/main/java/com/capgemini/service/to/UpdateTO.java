package com.capgemini.service.to;

public class UpdateTO {

	private Long id;
	private String name;
	private String surname;
	private String aboutMe;
	private String lifeMotto;
	private String login;
	private String email;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UpdateTO() {

	}

	public UpdateTO(String email, String login, String lifeMotto, String aboutMe, String surname, String name) {
		this.email = email;
		this.login = login;
		this.lifeMotto = lifeMotto;
		this.aboutMe = aboutMe;
		this.surname = surname;
		this.name = name;
	}

}
