package restassured.reqres;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class JSON_localserver 
{
	@SuppressWarnings("unchecked")
	@Test
	public void jsonlocaltest()
	{
		RestAssured.baseURI = "http://localhost:3000/";
		//getting the user details
		given().get("/users").then().statusCode(200).log().all();
		//getting the subject name
		given().param("name", "automation").get("/subjects").then().statusCode(200).log().all();
		
		//putting the Value using the MAP
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject request = new JSONObject(map);
		request.put("firstname", "Lalit");
		request.put("lastname", "Kumar");
		request.put("job", "mechanical engineer");
		request.put("id", 004);
		request.put("subjectId", 4);
		
		
		given().contentType(ContentType.JSON).header("Content-Type","application/json").body(request.toJSONString()).when().post("/users").then().statusCode(201).log().all();
		
	}
}
