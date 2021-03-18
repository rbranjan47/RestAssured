package restassured.reqres;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;


public class postUser 
{
	@Test
	public void post_user()
	{
		Map<String , Object> mapp = new HashMap<String, Object>();
		mapp.put("name", "rabi"); 
		mapp.put("job", "software engineer");
		
		System.out.println(mapp);
		
		//to convert it into JSON Object we can use JSONObject
		JSONObject jso = new JSONObject(mapp);
		System.out.println(jso);
		System.out.println(jso.toJSONString());
		
		given().header("Content-Type","application/json").contentType(ContentType.JSON).body(jso.toJSONString()).when()
		.post("https://reqres.in/api/register").then().statusCode(201).log().all();
	}
}
