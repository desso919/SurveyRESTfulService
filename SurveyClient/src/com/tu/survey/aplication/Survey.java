package com.tu.survey.aplication;

import java.util.ArrayList;
import java.util.List;

public class Survey {

	private String name;
	private List<Question> questions = new ArrayList<Question>();

	public Survey() {}

	public Survey(String name) {
		this.name = name;
	}

	public Survey(String name, List<Question> questions) {
		this.name = name;
		this.questions = questions;
	}

	public String getName() {
		return name;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}
	
	public void addQuestion(Question question) {
		if(question != null) {
			questions.add(question);
		}
	}
}
