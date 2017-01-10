package com.tu.survey.aplication;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Survey {

	private String name;
	private List<Question> questions = new ArrayList<Question>();

	public Survey() {
		this.name = "Demo survey";
	}

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
	
	public String toJSON() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
}
