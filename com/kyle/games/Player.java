package com.kyle.games;

public class Player {

	private String username;
	private int numOfWins;
	private int numOfLosses;

	public int getWins() {
		return numOfWins;
	}

	public int getLosses() {
		return numOfLosses;
	}

	public void addWin() {
		numOfWins++;
	}

	public void addLoss() {
		numOfLosses++;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
}
