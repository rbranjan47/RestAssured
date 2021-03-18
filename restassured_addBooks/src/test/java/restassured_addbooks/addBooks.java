package restassured_addbooks;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class addBooks 
{

	@Test(dataProvider = "booksData", priority = 1)
	public void addbooks_post(String isbn, String asile)
	{
		RestAssured.baseURI = "http://216.10.245.166";
		//reading the response
		String response_post = given().contentType(ContentType.JSON).header("Content-Type","application/json")
				.body(payloads_addbooks.body_addbooks(isbn, asile))
				.when().post("/Library/Addbook.php").then()
		.statusCode(200).extract().asString();
		
		System.out.println(response_post);
		
		JsonPath js = new JsonPath(response_post);
		String id = js.get("ID");
		String msg = js.get("Msg");
		System.out.println("book :"+id+" "+msg);
		
		//delete users
		String response_delete = given().contentType(ContentType.JSON).header("Content-Type","application/json").body(payloads_addbooks.body_deletebooks(id))
		.when().delete("/Library/DeleteBook.php").then().statusCode(200).extract().asString();
		
		JsonPath jso = new JsonPath(response_delete);
		String response_message = jso.get("msg");
		System.out.println("id :"+id+" "+response_message);
		System.out.println("--------------------------------");
	}
	
	//data driven
	@DataProvider(name = "booksData")
	public Object[][] data_provider()
	{
		return new Object[][] {{"bcd","142"},{"ajg","845"},{"mlh","387"}};
	}
	
}
