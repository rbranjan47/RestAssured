package restassured.restassured_demo;

import io.restassured.path.json.JsonPath;

// we are using this in place for 

public class ReUsable_Json 
{
	public static JsonPath raToJason(String js_response)
	{
		JsonPath js_get=new JsonPath(js_response);
		return js_get;
	}
}
