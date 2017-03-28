package com.capgemini.service.to;

public class UserTO {
	
	private Long id;
	private String login;
	private String password;
	private String email;
	private ProfileTO profile;
	private StatisticsTO statistics;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public ProfileTO getProfile() {
		return profile;
	}

	public void setProfile(ProfileTO profile) {
		this.profile = profile;
	}

	public StatisticsTO getStatistics() {
		return statistics;
	}

	public void setStatistics(StatisticsTO statistics) {
		this.statistics = statistics;
	}

	public UserTO(){
		
	}
	
	public UserTO(String login, String password, String email, ProfileTO profile, StatisticsTO statistics) {
		this.login = login;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.statistics = statistics;
	}
	
}
