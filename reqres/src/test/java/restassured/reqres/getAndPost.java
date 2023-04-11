package restassured.reqres;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class getAndPost {
	@Test
	public void restAssuredTest() {
		RestAssured.baseURI = "https://reqres.in/api";
		
		given().get("/users?page=2").then().statusCode(200);
		
		Map<String, Object> mapsPost = new HashMap<String, Object>();
		mapsPost.put("name", "Rabi Ranjan Kumar");
		mapsPost.put("job", "Software Engineer");
		 
		JSONObject request = new JSONObject(mapsPost);
		
		given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(request.toJSONString()).
		when().post("/users").then().statusCode(201).log().all();
		
		
	}
}
