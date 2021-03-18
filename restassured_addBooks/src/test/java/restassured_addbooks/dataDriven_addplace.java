package restassured_addbooks;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class dataDriven_addplace 
{
	@Test
	public void addplace() throws IOException
	{
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//getting the response
		String response = given().log().all().queryParam("key", "qaclick123").contentType(ContentType.JSON).header("Content-Type","application/json")
		.body(Files.readAllBytes(Paths.get("C:\\Users\\Lenovo\\Documents\\addPlace.JSON"))).when().post("/maps/api/place/add/json")
        .then().statusCode(200).extract().asString();
		
		System.out.println(response);
		//getting response from JSON Path
		JsonPath jso = new JsonPath(response);
		System.out.println("Status :"+jso.get("status")+" "+"Place ID :"+jso.get("place_id")+" "+"Scope :"+jso.get("scope"));
	}
}
