package restassured.reqres;

import io.restassured.path.json.JsonPath;

public class json_response 
{
	public static JsonPath Json_respons(String response)
	{
		JsonPath js_get=new JsonPath(response);
		return js_get;
	}
}
