package restAssuredYT.restAssureBasics;

import org.apache.commons.codec.binary.Base64;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class basicAuthTest {

	@Test
	public void basicAuthTestDemo() {
		/*
		 * username: rbranjan47 password: RabiQA123@
		 * 
		 * URL: https://bookstore.toolsqa.com/BookStore/v1/Books
		 */

		RequestSpecification requests = RestAssured.given();
		requests.baseUri("https://bookstore.toolsqa.com/");
		requests.basePath("BookStore/v1/Books");
		// basic Auth
		// requests.auth().basic("rbranjan47", "RabiQA123@");

		String credentials = "rbranjan47:RabiQA123@";
		// passing username and password as encoded form
		byte[] endcodeCreds = Base64.encodeBase64(credentials.getBytes());
		
		//convert encode byte to String
		String encodedCredString = new String(endcodeCreds);
		//Basic Header
		requests.header("Authorization", "Basic"+encodedCredString);
		requests.contentType(ContentType.JSON);
		
		String payloadBODY = "{\r\n"
				+ "  \"userId\": \"9b5f49ab-eea9-45f4-9d66-bcf56a531b85\",\r\n"
				+ "  \"collectionOfIsbns\": [\r\n"
				+ "    {\r\n"
				+ "      \"isbn\": \"9781449331818\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		Response responses = requests.body(payloadBODY).when().post();

		ResponseSpecification responseSpec = RestAssured.expect();
		responseSpec.statusCode(200);
		responseSpec.time(Matchers.lessThan(5000L));

		requests.then().spec(responseSpec).log().all();
	
		System.out.println(responses.prettyPrint());

	}
}
