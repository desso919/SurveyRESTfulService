package com.tu.survey.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.tu.survey.aplication.Answer;
import com.tu.survey.aplication.Question;
import com.tu.survey.aplication.Survey;
import com.tu.survey.aplication.Surveys;

public class SurveyClient {
	private static Survey currentSurvey = null;

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		Scanner input = new Scanner(System.in);

		while (true) {
			System.out.println("\n------------------------------------------------------------------------------ \n");
			System.out.println("Please select a option!");

			System.out.println("1. See all surveys.");
			System.out.println("2. Start survey.");
			System.out.println("3. Submit survey.");
			System.out.println("4. Exit. \n");

			int choice = input.nextInt();

			if (choice == 4) {
				break;
			}

			switch (choice) {
			case 1: {
				System.out.println(" All surveys return from the server: ");
				Surveys surveys = getAllSurveys();
				printAllSurveys(surveys);
			} break;
			case 2: {	
				currentSurvey = getSurvey();
				if (currentSurvey != null) {
					printSurvey(currentSurvey);
				}
			} break;
			case 3: {
				if (currentSurvey != null) {
					submitSurvey(currentSurvey);
				} else {
					System.out.println("Please first select a survey!");
				}
			} break;
			default: break;
			}
		}

	}

	private static Surveys getAllSurveys() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Client client = Client.create();

		WebResource resource = client.resource("http://localhost:8080/SurveyRESTfulService/survey/surveys");
		String responce = resource.accept(MediaType.APPLICATION_JSON).get(String.class);

		return mapper.readValue(responce, Surveys.class);
	}

	private static void printAllSurveys(Surveys surveys) {
		int number = 0;
		for (Survey survey : surveys.getSurveys()) {
			System.out.println("   " + (++number) + ". " + survey.getName());
		}
	}

	private static Survey getSurvey() throws JsonParseException, JsonMappingException, IOException {
		Scanner input = new Scanner(System.in);
		ObjectMapper mapper = new ObjectMapper();
		Client client = Client.create();
		
		System.out.println("Please enter the name of the survey!");
		String surveyName = input.nextLine();

		String url = "http://localhost:8080/SurveyRESTfulService/survey/surveys/" + replace(surveyName);
		WebResource resource = client.resource(url);
		String response = resource.accept(MediaType.APPLICATION_JSON).get(String.class);

		if (response.equals("{}")) {
			System.out.println("Could not find survey with this name!");
			return null;
		} else {
			return mapper.readValue(response, Survey.class);
		}
	}

	private static void submitSurvey(Survey survey) throws JsonProcessingException {
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to submit the survey (yes/no)?");
		String submit = input.nextLine();
		if (submit.equalsIgnoreCase("yes") || submit.equalsIgnoreCase("y")) {
			Client client = Client.create();
			ObjectMapper mapper = new ObjectMapper();

			String json = mapper.writeValueAsString(survey);
			WebResource resource = client.resource("http://localhost:8080/SurveyRESTfulService/survey/surveys");

			resource.path("/submit").type(MediaType.APPLICATION_JSON).post(json);
			System.out.println("The survey was successfully submited!");
		} else {
			System.out.println("The survey is not yet submited!");
		}
	}

	private static void printSurvey(Survey survey) throws JsonProcessingException {
		Scanner input = new Scanner(System.in);
		System.out.println("Start survey: " + survey.getName());
		List<Question> questions = survey.getQuestions();

		for (Question question : questions) {
			System.out.println("\n" + question.getQuestion());

			if (question.isOpenQuestion()) {
				System.out.println("Please enter your answer! \n");
				question.setOpenAnswer(input.nextLine());
			} else {
				if (question.isMultipleAnswers()) {
					System.out.println("Please choose one or more!");
					printAnswers(question);
				} else {
					System.out.println("Please choose only one!");
					printAnswers(question);
				}
				waitForUserToAnswer(question.getAnswers());
			}
		}
	}

	private static void waitForUserToAnswer(List<Answer> options) {
		Scanner input = new Scanner(System.in);
		String userAnswers = input.next();
		List<String> answers = Arrays.asList(userAnswers.split(","));

		for (String answer : answers) {
			try {
				int number = Integer.parseInt(answer);
				options.get(number - 1).setAsSelected();
			} catch (NumberFormatException e) {
			}
		}

	}

	private static int printAnswers(Question question) {
		int option = 0;
		List<Answer> answers = question.getAnswers();
		for (Answer answer : answers) {
			System.out.println("   " + (++option) + ". " + answer.getAnswer());
		}
		return option;
	}

	private static String replace(String str) {
	    String[] words = str.split(" ");
	    StringBuilder sentence = new StringBuilder(words[0]);

	    for (int i = 1; i < words.length; ++i) {
	        sentence.append("%20");
	        sentence.append(words[i]);
	    }

	    return sentence.toString();
	}
}
