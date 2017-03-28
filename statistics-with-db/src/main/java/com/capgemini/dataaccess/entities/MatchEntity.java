package com.capgemini.dataaccess.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MATCHES")
public class MatchEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Date gameDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "winnerId", nullable = false)
	private UserEntity winner;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "looserId", nullable = false)
	private UserEntity looser;

	public UserEntity getWinner() {
		return winner;
	}

	public void setWinner(UserEntity winner) {
		this.winner = winner;
	}

	public UserEntity getLooser() {
		return looser;
	}

	public void setLooser(UserEntity looser) {
		this.looser = looser;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	public MatchEntity() {

	}

	public MatchEntity(UserEntity winner, UserEntity looser) {
		this.setWinner(winner);
		this.setLooser(looser);
		this.setGameDate(new Date());
	}
}