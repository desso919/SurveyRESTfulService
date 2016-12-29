package com.tu.survey.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tu.survey.aplication.Question;
import com.tu.survey.aplication.Survey;

@Path("/questions")
public class SurveyService {
	
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response convertFtoC() throws JSONException, JsonProcessingException {		  
	    ObjectMapper mapper = new ObjectMapper();
			
		Survey servey = new Survey(" Best city in Bulgaria");
		servey.addQuestion(new Question("Question 1" , true, "option 1", "option 2"));
		servey.addQuestion(new Question("Question 2" , true, "option 1", "option 2"));

		//Object to JSON in String
		String jsonInString = mapper.writeValueAsString(servey);
	
		return Response.status(200).entity(jsonInString).build();
	  }

	  @POST
	  @Path("/submit")
	  @Consumes(MediaType.APPLICATION_JSON)
	  public Response submitSurvey(String data) throws JSONException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		  
		//JSON from String to Object
		if(data != null) {
		   Survey obj = mapper.readValue(data, Survey.class);
		}

		
		return Response.status(200).entity("OKeeeee").build();
	  }
}
