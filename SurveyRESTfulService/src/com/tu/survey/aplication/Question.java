package com.tu.survey.aplication;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private String question;
	private List<String> answers = new ArrayList<String>();
	private boolean isMultipleAnswers;
	private boolean isOpenQuestion;
	
	public Question(String question) {
		this.question = question;
		this.isMultipleAnswers = false;
		this.isOpenQuestion = true;
	}
	
	public Question(String question, List<String> answers, boolean isMultipleAnswers) {		
		this.isMultipleAnswers = isMultipleAnswers;
		this.isOpenQuestion = false;
		this.question = question;
		this.answers = answers;
	}
	
	public Question(String question, boolean isMultipleAnswers, String... answers) {
		this.isMultipleAnswers = isMultipleAnswers;
		this.isOpenQuestion = false;
		
		for (String answer: answers) {
			this.answers.add(answer);
		}
		
		this.question = question;
	}
	
	public boolean isOpenQuestion() {
		return isOpenQuestion;
	}

	public boolean isMultipleAnswers() {
		return isMultipleAnswers;
	}
	
	public String getQuestion() {
		return question;
	}

	public List<String> getAnswers() {
		return answers;
	}
	
	
	
	
}