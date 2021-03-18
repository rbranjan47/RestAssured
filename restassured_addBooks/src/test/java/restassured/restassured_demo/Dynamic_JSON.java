package restassured.restassured_demo;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.restassured_payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Dynamic_JSON
{
	@Test(dataProvider = "booksData")
	public void addbook(String isbn, String aisle)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		//getting response to ADD BOOK
		String response = given().headers("Content-Type","application/json").body(restassured_payload.addbook("kjx","707"))
				.when().post("/Library/Addbook.php").then().statusCode(200).extract().response().asString();
		
		JsonPath js=ReUsable_Json.raToJason(response);
		String id=js.get("ID");
		System.out.println(id);
		
		//DELETE CARD
		
	}
	
	
	//Using DataProvider, Set of Data test, by sending it as array
	@DataProvider(name = "booksData")
	public Object[][] get_data()
	{
		//array = collection of elements
		//multi-dimensional array= collection of array
		return new Object[][] {{"vtvx","768"},{"ntvc","989"},{"jkas","342"}};
	}
}
