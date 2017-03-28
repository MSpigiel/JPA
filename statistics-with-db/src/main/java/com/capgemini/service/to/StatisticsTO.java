package com.capgemini.service.to;

import com.capgemini.LevelRequirements;

public class StatisticsTO {

	private Long id;
	private Integer matchWon;
	private Integer matchLost;
	private Integer matchDraw;
	private Integer matchTotal;
	private Long Points;
	private Integer currentLevel;
	private String levelName;
	
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getMatchWon() {
		return matchWon;
	}

	public void setMatchWon(Integer matchWon) {
		this.matchWon = matchWon;
	}

	public Integer getMatchLost() {
		return matchLost;
	}

	public void setMatchLost(Integer matchLost) {
		this.matchLost = matchLost;
	}

	public Long getPoints() {
		return Points;
	}

	public void setPoints(Long Points) {
		this.Points = Points;
	}

	public Integer getMatchDraw() {
		return matchDraw;
	}

	public void setMatchDraw(Integer matchDraw) {
		this.matchDraw = matchDraw;
	}

	public Integer getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(Integer currentLevel) {
		this.currentLevel = currentLevel;
	}

	public Integer getMatchTotal() {
		return matchTotal;
	}

	public void setMatchTotal(Integer matchTotal) {
		this.matchTotal = matchTotal;
	}
	
	public StatisticsTO(){
		this.setCurrentLevel(1);
		this.setMatchDraw(0);
		this.setMatchLost(0);
		this.setMatchTotal(0);
		this.setMatchWon(0);
		this.setPoints(0L);
		this.setLevelName(LevelRequirements.NEWBIE.name());
	}

}
