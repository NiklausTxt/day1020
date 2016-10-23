package com.iwantbesuper;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private int id;
	private String value;
	private int credit;
	private int player_id;
	private boolean isOpen;
	private List<Answer> answers=new ArrayList<>();
	
	
	public Question(int id, String value, int credit, int player_id) {
		super();
		this.id = id;
		this.value = value;
		this.credit = credit;
		this.player_id = player_id;
		this.isOpen=true;
	}
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
	
	
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	@Override
	public String toString() {
		if(isOpen){
			return "[UNSLOVED] Question [id=" + id +", credit=" + credit + ", player_id=" + player_id+
					" , value=" + value +  "]"+ " number of answers:"+answers.size();
		}else{			
			return "[SLOVED]   Question [id=" + id +", credit=" + credit + ", player_id=" + player_id+
					" , value=" + value +  "]"+ " number of answers:"+answers.size();
		}
	}

//	@Override
//	public String toString() {
//		String string;
//		if (isOpen) {
//			string = "[UNSLOVED]" + "Question [id=" + id + ",value=" + value + ", credit="
//					+ credit + ", ask by player" + player_id + ", number of answers:"+answers.size()+"]";
//		} else {
//			string =  "[SLOVED]  " + "Question [id=" + id + ",value=" + value + ", credit="
//					+ credit + ", ask by player" + player_id + ", number of answers:"+answers.size()+"]";
//		}
//		return string;
//	}
	
}
