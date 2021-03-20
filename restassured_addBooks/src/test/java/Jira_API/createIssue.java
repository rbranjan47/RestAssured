package Jira_API;

import org.testng.annotations.Test;

import files.jira_BODY;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*; 

public class createIssue 
{
	@Test
	public void createissue()
	{
		
		RestAssured.baseURI = "http://localhost:8080";
		//LOGIN Scenarios using Post Method
		String response_Session= given().log().all().contentType(ContentType.JSON).header("Content-Type","application/json").body(jira_BODY.loggedIN())
		.when().post("/rest/auth/1/session").then().statusCode(200).extract().asString();
		
		//Using JSONPath we will extract the Value of 
		JsonPath js = new JsonPath(response_Session);
		String name = js.get("name");
		String value = js.get("value");
		
		System.out.println("Successfully loggedIn with name :"+name+"value :"+value);
		
		
		//Geting Response for POSTING
		String response_issue = given().log().all().contentType(ContentType.JSON).header("Content-Type","application/json")
				.header("Cookie",name+"="+value)
		.body(jira_BODY.createBody()).when().post("/rest/api/2/issue").then().statusCode(201).extract().asString();
		
		JsonPath jso = new JsonPath(response_issue);
		String id = jso.get("id");
		String key = jso.get("key");
		
		System.out.println("Successfully added issue with "+"Id :"+id+"Key :"+key);
		
		//commenting on Last Created issue
		String response_comments = given().pathParam("id", id).log().all().contentType(ContentType.JSON).header("Content-Type","application/json").body(jira_BODY.createBody())
		.when().post("rest/api/2/issue/{id}/comment").then().statusCode(201).extract().asString();
		
		JsonPath jsop = new JsonPath(response_comments);
		String email = jsop.get("emailAddress");
		String name_display = jsop.get("displayName");
		
		System.out.println("Succesfully added with "+email+" & "+name_display);
		
	}
}
