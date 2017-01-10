package com.tu.survey.aplication;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Surveys {

	private List<Survey> surveys = new ArrayList<Survey>();

	public List<Survey> getSurveys() {
		return surveys;
	}

	public boolean addSurvey(Survey survey) {
		if (survey != null) {
			surveys.add(survey);
			return true;
		}
		return false;
	}

	public Survey getSurvey(int index) {
		return surveys.get(index);
	}
	
	public Survey getSurvey(String name) {
		for (Survey survey : surveys) {
			if(survey.getName().equals(name)) {
				return survey;
			}
		}
		
		return null;
	}
	
	public boolean containsSurveyWithName(String name) {
		for (Survey survey : surveys) {
			if(survey.getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	public String toJSON() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
}
