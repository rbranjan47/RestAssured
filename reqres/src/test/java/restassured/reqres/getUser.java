package restassured.reqres;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class getUser 
{

	//gettting response
	@Test (priority = 1)
	public void get_user()
	{
		Response response = get("https://reqres.in/api/users?page=2");
		//response as string
		System.out.println(response.asString());
		//response Body
		System.out.println(response.getBody().asString());
		//status code
		System.out.println(response.getStatusCode());
		//content type
		System.out.println(response.header("content-type"));
		//status line
		System.out.println(response.getStatusLine());
		//get time
		System.out.println(response.getTime());
		
		//checking Status code
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test(priority = 2)
	 public void test_u()
	{
		given().get("https://reqres.in/api/users?page=2")
		.then().statusCode(200)
		.body("data.id", equalTo(2));
	}
}
