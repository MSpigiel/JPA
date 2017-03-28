package com.capgemini;

public enum LevelRequirements {
	NEWBIE(1, 0, 0, 0), 
	WEAKLING(2, 301, 5, 0.8), 
	BEGINNER(3, 601, 20, 0.16), 
	EXPERIENCED_BEGINNER(4, 1201, 45, 0.24), 
	MIDDLEBROW(5, 2401, 80, 0.32), 
	EXPERIENCED_MIDDLEBROW(6, 4801, 125, 0.40), 
	ADVANCED(7, 9601, 180, 0.48), 
	PROFESSIONAL(8, 19201, 245, 0.56), 
	MASTER(9, 31401, 320, 0.64), 
	CHUCK_NORRIS_OF_CHESS(10, 76801, 405, 0.72);

	private int level;
	private int pointsRequired;
	private int gamesPlayedRequired;
	private double percentageWinsRequired;
	
	private LevelRequirements(int level, int pointsRequired, int gamesPlayedRequired, double percentageWinsRequired) {
		setLevel(level);
		setPointsRequired(pointsRequired);
		setGamesPlayedRequired(gamesPlayedRequired);
		setPercentageWinsRequired(percentageWinsRequired);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPointsRequired() {
		return pointsRequired;
	}

	public void setPointsRequired(int pointsRequired) {
		this.pointsRequired = pointsRequired;
	}

	public int getGamesPlayedRequired() {
		return gamesPlayedRequired;
	}

	public void setGamesPlayedRequired(int gamesPlayedRequired) {
		this.gamesPlayedRequired = gamesPlayedRequired;
	}

	public double getPercentageWinsRequired() {
		return percentageWinsRequired;
	}

	public void setPercentageWinsRequired(double percentageWinsRequired) {
		this.percentageWinsRequired = percentageWinsRequired;
	}
	
	
}
