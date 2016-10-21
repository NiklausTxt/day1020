package com.iwantbesuper;

public class Answer {
	private String value;
	private int question_id;
	private int player_id;
	private char best;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public char getBest() {
		return best;
	}
	public void setBest(char best) {
		this.best = best;
	}
	
}
