package com.capgemini;

public enum BasePoints {
	A(-9,1,1), B(-8,1,1), C(-7,1,1), D(-6,1,1), E(-5,1,1), F(-4,2,1), G(-3,5,3), H(-2,10,7), I(-1,20,15),
	J(0,40,30), K(1,80,60), L(2,160,120), M(3,320,240), N(4,640,480), O(5,1280,960), P(6,2560,1920), 
	Q(7,5120,3840), R(8, 10240, 7680), S(9, 20480, 15360);
	
	private int levelDifference;
	private int profitBase;
	private int lossBase;
	
	private BasePoints(int levelDifference, int profitBase, int lossBase){
		setLevelDifference(levelDifference);
		setProfitBase(profitBase);
		setLossBase(lossBase);
	}

	public int getLevelDifference() {
		return levelDifference;
	}

	public void setLevelDifference(int levelDifference) {
		this.levelDifference = levelDifference;
	}

	public int getProfitBase() {
		return profitBase;
	}

	public void setProfitBase(int profitBase) {
		this.profitBase = profitBase;
	}

	public int getLossBase() {
		return lossBase;
	}

	public void setLossBase(int lossBase) {
		this.lossBase = lossBase;
	}
	
}
