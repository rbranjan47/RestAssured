package restassured.reqres;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class getUsers 
{
	@Test
	public void get_Users()
	{
	RestAssured.baseURI = "https://reqres.in/api/users?page=2";
	given().get("https://reqres.in/api/users?page=2").then().statusCode(200)
	.body("data[5].id", equalTo(12)).body("data[5].email", equalTo("rachel.howell@reqres.in"));
	
	}
}