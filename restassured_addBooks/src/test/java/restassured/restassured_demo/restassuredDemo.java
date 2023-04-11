package restassured.restassured_demo;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.restassured_payload;

public class restassuredDemo
{
	public static void main(String[] args)
	{
		//Validating the API's working fine or not, by 
		
		//given----> all input details
		//when---->Submit the reponse, HTTP method
		//then----> Validate the Response 
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		
		//getting response Add Place
		String response=given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json").body(restassured_payload.addplace())
				.when().post("maps/api/place/add/json?qaclick123")
				       .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				       .header("Content-Length", "<calculated when request is sent>").extract().response().asString();
		System.out.println(response);
		
		//using the response value, by the class JSON path
		JsonPath js = new JsonPath(response);
		String placeID=js.getString("place_id");
		System.out.println("Place ID :"+placeID);
		
		//Update Place
		String new_address="bhuli c block q.no.-441 bhuli nagar";
		given().log().all().headers("Content-Type","application/json").body("{\r\n"
				+ "    \"p lace_id\":\""+placeID+"\",\r\n"
				+ "    \"address\":\""+new_address+"\",\r\n"
				+ "    \"key\":\"qaclick123\"\r\n"
				+ "}").when().put("maps/api/place/update/json?key=qaclick123").then().assertThat().statusCode(200)
		.body("msg",equalTo("Address successfully updated"));
		
		
		//Get Place( we will not use Header as it is not containing the body)
		String getplace_response = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().asString();
		
		
		//calling from the Reusable_Json class
		JsonPath jsget=ReUsable_Json.raToJason(getplace_response);
		//taking out the address to verify the above PUT method
		String get_address=jsget.getString("address");
		System.out.println(get_address);
		Assert.assertEquals(get_address, new_address);
		
		
	}
}
