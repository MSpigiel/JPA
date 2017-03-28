package com.capgemini.dataaccess.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import com.capgemini.LevelRequirements;

@Entity
@Table(name = "STATISTICS")
public class StatisticsEntity extends AbstractEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private Integer matchWon;

	@Column(nullable = false)
	private Integer matchLost;

	@Column(nullable = false)
	private Integer matchDraw;

	@Column(nullable = false)
	private Integer matchTotal;

	@Column(nullable = false)
	private Long points;

	@Column(nullable = false)
	private Integer currentLevel;

	@Column(nullable = false)
	private String levelName;

	@PostUpdate
	private void updateLevel() {
		if (currentLevel == 10)
			return;
		if (points >= LevelRequirements.values()[currentLevel].getPointsRequired()
				&& matchTotal >= LevelRequirements.values()[currentLevel].getGamesPlayedRequired()
				&& (double) (matchWon / matchTotal) >= LevelRequirements.values()[currentLevel]
						.getPercentageWinsRequired()) {
			this.levelName = LevelRequirements.values()[currentLevel].name();
			currentLevel++;
		}
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
		return points;
	}

	public void setPoints(Long Points) {
		this.points = Points;
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

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public StatisticsEntity() {
		this.setCurrentLevel(0);
		this.setMatchDraw(0);
		this.setMatchLost(0);
		this.setMatchTotal(0);
		this.setMatchWon(0);
		this.setModificationCounter(0);
		this.setPoints(0L);
		this.setLevelName(LevelRequirements.NEWBIE.name());

	}

	public StatisticsEntity(Integer currentLevel, Integer draw, Integer lost, Integer total, Integer won, Long points,
			LevelRequirements levelName) {
		this.setCurrentLevel(currentLevel);
		this.setMatchDraw(draw);
		this.setMatchLost(lost);
		this.setMatchTotal(total);
		this.setMatchWon(won);
		this.setModificationCounter(0);
		this.setPoints(points);
		this.setLevelName(levelName.name());
	}
}
