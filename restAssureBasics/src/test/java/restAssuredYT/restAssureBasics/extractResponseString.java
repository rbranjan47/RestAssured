package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class extractResponseString {
	@Test
	public void extractResponseStringTest() {
		// Build Request
		String responses = RestAssured
		.given()
		.baseUri("https://restful-booker.herokuapp.com/")
		.basePath("booking").body("{\r\n"
				+ "    \"firstname\" : \"Jim\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}")

		.contentType(ContentType.JSON)
		// requestSpecification.contentType("application/json");
		// requestSpecification.accept("application/json");

		// hit request
		.post()
		.then()
		.extract()
		.body()
		.asPrettyString();
		
		System.out.println(responses);
	}
}
