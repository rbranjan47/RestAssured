package restassured.restassured_demo;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJSON_Files 
{
	public class Dynamic_JSON {
		@Test(dataProvider = "booksData")
		public void addbook() throws IOException 
		{
			RestAssured.baseURI = "http://216.10.245.166";
			// getting response to ADD BOOK
			String response = null;
			try
			{
				response = given().headers("Content-Type", "application/json")
						.body(Files.readAllBytes(Paths.get("C:\\Users\\Lenovo\\Documents\\addBooks.JSON"))).when()
						.post("/Library/Addbook.php").then()
						.statusCode(200).extract().response().asString();
				
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			JsonPath js = ReUsable_Json.raToJason(response);
			String id = js.get("ID");
			System.out.println(id);

		}
	}
}
