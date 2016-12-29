package com.tu.survey.aplication;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private String question;
	private List<String> answers = new ArrayList<String>();
	private boolean multipleAnswers;
	private boolean openQuestion;
	
	public Question() {}
	
	public Question(String question) {
		this.question = question;
		this.multipleAnswers = false;
		this.openQuestion = true;
	}
	
	public Question(String question, List<String> answers, boolean isMultipleAnswers) {		
		this.multipleAnswers = isMultipleAnswers;
		this.openQuestion = false;
		this.question = question;
		this.answers = answers;
	}
	
	public Question(String question, boolean isMultipleAnswers, String... answers) {
		this.multipleAnswers = isMultipleAnswers;
		this.openQuestion = false;
		
		for (String answer: answers) {
			this.answers.add(answer);
		}
		
		this.question = question;
	}

	public boolean isOpenQuestion() {
		return openQuestion;
	}

	public boolean isMultipleAnswers() {
		return multipleAnswers;
	}
	
	public String getQuestion() {
		return question;
	}

	public List<String> getAnswers() {
		return answers;
	}
	
	@Override
	public String toString() {
		return  question;
	}	
}
