package com.iwantbesuper;

public class Question {
	private String value;
	private int credit;
	private int player_id;
	private char isOpen;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public char getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(char isOpen) {
		this.isOpen = isOpen;
	}
	@Override
	public String toString() {
		return "Question [value=" + value + ", credit=" + credit + ", player_id=" + player_id + ", isOpen=" + isOpen
				+ "]";
	}

	
}
