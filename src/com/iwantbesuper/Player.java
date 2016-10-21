package com.iwantbesuper;

import java.util.List;

public class Player {
	private int id;
	private String name;
	private int score;
	private String username;
	private String password;
	List<Question> questions=null;
	List<Answer> answers=null;
	
	
	
	
	public Player() {
		super();
	}
	
	public Player(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
	}
	public Player(int id, String name, int score, String username, String password) {
		super();
		this.id = id;
		this.name = name;
		this.score = score;
		this.username = username;
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public int getId() {
		return id;
	}
	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", score=" + score + "]";
	}
	
	
	

	
	
}
