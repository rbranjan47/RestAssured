package Jira_API;

import org.testng.annotations.Test;

import files.jira_BODY;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File; 

public class JIRA 
{
	@Test
	public void jiraAPI()
	{
		
		RestAssured.baseURI = "http://localhost:8080";
		
		//Using session filter so that, it understand for every request a new session is created every time
		SessionFilter session = new SessionFilter();
		
		//LOGIN Scenarios using Post Method
		String response_Session= given().log().all().contentType(ContentType.JSON).header("Content-Type","application/json").body(jira_BODY.loggedIN())
		.when().post("/rest/auth/1/session").then().extract().asString();
		
		System.out.println(response_Session);
		
		//Using JSONPath we will extract the Value of name and value
		JsonPath js = new JsonPath(response_Session);
		String name = js.get("session.name");
		String value = js.get("session.value");
		
		System.out.println("Successfully loggedIn with name :"+name+" & value :"+value);
		
		
		//Geting Response for POSTING
		String response_issue = given().log().all().contentType(ContentType.JSON).header("Content-Type","application/json")
				.cookie(name , value)
		.body(jira_BODY.createBody()).filter(session).filter(session).when().post("/rest/api/2/issue").then().extract().asString();
		
		JsonPath jso = new JsonPath(response_issue);
		String id = jso.get("id");
		String key = jso.get("key");
		
		System.out.println("Successfully added issue with "+"Id :"+id+"Key :"+key);
		
		
		
		//commenting on Last Created issue
		String response_comments = given().pathParam("id", id).log().all().contentType(ContentType.JSON).header("Content-Type","application/json")
				.cookie(name , value).body(jira_BODY.commentsbody())
		.filter(session).when().post("rest/api/2/issue/{id}/comment").then().extract().asString();
		
		JsonPath jsop = new JsonPath(response_comments);
		String email = jsop.get("author.emailAddress");
		String name_display = jsop.get("updateAuthor.name");
		String comment_id = jsop.get("id");
		
		System.out.println("Succesfully added with "+email+" & "+name_display +" & id :"+comment_id); 
		
		
		
		//add attachment in existing issue
		given().log().all().header("X-Atlassian-Token", "no-check").filter(session).pathParam("id", id).header("Content-Type","multipart/form-data")
		.multiPart("file", new File("C:\\Users\\Lenovo\\Atlassian\\Application Data\\Jira\\data\\attachments\\Jira.txt"))
		.when().post("/rest/api/2/issue/{id}/attachments").then().log().all();
		
		//get issues
		given().filter(session).pathParam("id", id).log().all().when()
		.get("/rest/api/2/issue/{id}").then().log().all().extract().response().asString();
		
		//getting the Comments by using the Query Parameters
		String commentsissuedetails = given().filter(session).pathParam("id", id).queryParam("fields", "comment").log().all().when()
		.get("/rest/api/2/issue/{id}").then().log().all().extract().response().asString();
		
		
		
		//Now we can Iterate the Body to Check whether the Added commnt is present or not
		//Let suppose more comments is present in the Seqeunce
		JsonPath jsP = new JsonPath(commentsissuedetails);
		int commentCount = jsP.getInt("fields.comment.comments.size()");
		for (int i=0; i<commentCount ;i++)
		{
			String commentissueId = jsP.get("fields.comment.comments.id["+i+"]");
			if (commentissueId.equalsIgnoreCase(comment_id)) //getting from the JSON request while adding comment
			{
				String Body = jsP.get("fields.comment.comments["+i+"].body");
				System.out.println(Body);
				break;
			}
		}
		
		
	}
}
