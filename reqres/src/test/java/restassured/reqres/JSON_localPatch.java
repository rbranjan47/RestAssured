package restassured.reqres;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class JSON_localPatch 
{
	@SuppressWarnings("unchecked")
	@Test 
	public void jsonPatchtest() 
	{
		RestAssured.baseURI = "http://localhost:3000/";
		//first getting the users details in Local API
		given().log().all().contentType(ContentType.JSON).get("/users").then().statusCode(200).log().all();
		
		//Now, Patching/Updating the API
		Map<String, Object> map=new HashMap<String, Object>();
		//usinf JSONObject(google) to convert file into JSON object
		JSONObject js = new JSONObject(map);
		js.put("firstname", "Dhiraj");
		
		given().contentType(ContentType.JSON).header("Content-Type","application/json").body(js.toJSONString()).when().patch("/users/4").then().statusCode(200).log().all();
	}
}
