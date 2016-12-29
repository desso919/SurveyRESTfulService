package com.tu.survey.aplication;

import java.util.ArrayList;
import java.util.List;

public class Surveys {

	private List<Survey> surveys = new ArrayList<Survey>();

	public List<Survey> getSurveys() {
		return surveys;
	} 
	
	public boolean addSurvey(Survey survey) {
		if(survey != null) {
			surveys.add(survey);
			return true;
		}
		return false;
	}
}
