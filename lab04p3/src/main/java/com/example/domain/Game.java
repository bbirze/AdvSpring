package com.example.domain;

public class Game {

	private String gameDate;
	private String team1Name;
	private String team2Name;
	public String getGameDate() {
		return gameDate;
	}
	public void setGameDate(String gameDate) {
		this.gameDate = gameDate;
	}
	public String getTeam1Name() {
		return team1Name;
	}
	public void setTeam1Name(String team1Name) {
		this.team1Name = team1Name;
	}
	public String getTeam2Name() {
		return team2Name;
	}
	public void setTeam2Name(String team2Name) {
		this.team2Name = team2Name;
	}
	@Override
	public String toString() {
		return "Game [gameDate=" + gameDate + ", team1Name=" + team1Name + ", team2Name=" + team2Name + "]";
	}	
}
