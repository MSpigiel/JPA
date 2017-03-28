package com.capgemini.service.to;

import java.util.Date;

public class MatchTO {

	private Date gameDate;
	private UserTO winner;
	private UserTO looser;

	public Date getGameDate() {
		return gameDate;
	}

	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}

	public UserTO getWinner() {
		return winner;
	}

	public void setWinner(UserTO winner) {
		this.winner = winner;
	}

	public UserTO getLooser() {
		return looser;
	}

	public void setLooser(UserTO looser) {
		this.looser = looser;
	}

	public MatchTO() {

	}

	public MatchTO(UserTO winner, UserTO loser) {
		this.winner = winner;
		this.looser = loser;
		this.gameDate = new Date();
	}

}
