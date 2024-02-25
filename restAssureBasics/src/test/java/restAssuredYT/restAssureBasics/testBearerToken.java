package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class testBearerToken {

	// https://www.youtube.com/watch?v=25NAEM9Y6NY&list=PLbwZ_0ncMaxhB43YQKv9SjyrfbZCOYxX3
	// https://www.baeldung.com/rest-assured-tutorial
	
	//https://www.youtube.com/watch?v=a5Tc7B_c7IQ
	@Test
	public void testBearerToke() {
		RequestSpecification requestSpec = RestAssured.given();
		requestSpec.baseUri("https://bookstore.toolsqa.com/");
		requestSpec.basePath("Account/v1/GenerateToken");

		requestSpec.contentType(ContentType.JSON);

		String payloadBody = "{\r\n" + "  \"userName\": \"rbranjan47\",\r\n" + "  \"password\": \"RabiQA123@\"\r\n"
				+ "}";

		Response tokenResponse = requestSpec.body(payloadBody).post();

		tokenResponse.prettyPrint();

		String prittyPrintString = tokenResponse.getBody().asString();
		System.out.println(prittyPrintString);

		// Picking bearer Token
		String bearerToken = JsonPath.from(prittyPrintString).getString("token");
		System.out.println("Token: " + bearerToken);

		RequestSpecification requests2 = RestAssured.given();
		requests2.baseUri("https://bookstore.toolsqa.com/");
		requests2.basePath("BookStore/v1/Books");

		requests2.header("Authorization", "Bearer " + bearerToken);
		requests2.contentType(ContentType.JSON);

		String payloadBodyPostBooks = "{\r\n" + "  \"userId\": \"a178a104-e3c0-4f50-bbd5-3e4919f063e8\",\r\n"
				+ "  \"collectionOfIsbns\": [\r\n" + "    {\r\n" + "      \"isbn\": \"9781449325862\"\r\n" + "    }\r\n"
				+ "  ]\r\n" + "}";

		Response postBooksResponses = requests2.body(payloadBodyPostBooks).post();

		postBooksResponses.prettyPrint();

	}
}
