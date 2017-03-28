package com.capgemini.dataaccess.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import net.minidev.json.annotate.JsonIgnore;

@Entity
@Table(name = "USER")
public class UserEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 45)
	private String login;

	@JsonIgnore
	@Column(nullable = false, length = 45)
	private String password;

	@Column(nullable = false, length = 45)
	private String email;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "profileId")
	private ProfileEntity profile;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "statisticsId")
	private StatisticsEntity statistics;

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

	public ProfileEntity getProfile() {
		return profile;
	}

	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}

	public StatisticsEntity getStatistics() {
		return statistics;
	}

	public void setStatistics(StatisticsEntity statistics) {
		this.statistics = statistics;
	}

	public UserEntity(){
		
	}
	
	public UserEntity(String login, String password, String email, ProfileEntity profile, StatisticsEntity statistics) {
		this.login = login;
		this.password = password;
		this.email = email;
		this.profile = profile;
		this.statistics = statistics;
	}

}
