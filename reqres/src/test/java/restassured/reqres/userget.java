package restassured.reqres;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;


public class userget 
{
	
	@Test
	public void rest_user()
	{
		RestAssured.baseURI="https://reqres.in";
		
		//getting Users 
		String responsee=given().log().all().queryParam("page", "2").when().get("/api/users").then().assertThat()
		.statusCode(200).extract().asString();
		
		JsonPath js=json_response.Json_respons(responsee);
		String first = js.getString("first_name");
		String last = js.getString("last_name");
		System.out.println(first +" "+last);
		
		//getting response and verifying 
		String response_code = given().log().all().when().get("/api/users/2").then().assertThat()
				.statusCode(200).extract().asString();
		System.out.println(response_code);
		
		
		//Calling JSON reusable class
		JsonPath js_get=json_response.Json_respons(response_code);
		String first_name = js_get.getString("first_name");
		String last_name = js_get.getString("last_name");
		System.out.println("Name of user :"+ first_name+" "+last_name);
		
		
	}
}
