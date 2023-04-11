package restassured.reqres;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class getUser 
{

	//gettting response
	@Test
	public void get_user()
	{
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		
		
		//response as string
		System.out.println(response.asString());
		//response Body
		System.out.println("Body: "+ response.getBody().asString());
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
		
		
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 201);
	}
}
