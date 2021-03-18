package restassured.reqres;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class delete_parameters 
{
	@Parameters({"id"})
	@Test
	public void dataDriven_post(int id)
	{
		RestAssured.baseURI = "http://localhost:3000/";
		//getting the user details
		given().get("/users").then().statusCode(200).log().all();
		//getting the subject name
		given().param("name", "automation").get("/subjects").then().statusCode(200).log().all();
		
		
		//deleting the users
		given().when().delete("/users/"+id).then().statusCode(200).log().all();
    }
}