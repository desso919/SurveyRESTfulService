package com.tu.survey.aplication;

public class Answer {

	private String answer;
	private boolean selected;
	
	public Answer() {
		answer = "";
	}
	public Answer(String answer) {
		this.answer = answer;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setAsSelected() {
		this.selected = true;
	}
	
	public String getAnswer() {
		return answer;
	}
}
