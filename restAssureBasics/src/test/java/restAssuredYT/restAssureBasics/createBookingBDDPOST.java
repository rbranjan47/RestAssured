package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class createBookingBDDPOST {

	@Test
	public void createBookingBDDTest() {
		//Rest Assured also follow the Gherkin language i.e. Given, When, And, Then
		
		RestAssured
		.given()
		.log()
		.all()
		.baseUri("https://restful-booker.herokuapp.com/")
		.basePath("booking")
		.body("{\r\n"
				+ "    \"firstname\" : \"Tom\",\r\n"
				+ "    \"lastname\" : \"Smith\",\r\n"
				+ "    \"totalprice\" : 199,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2022-01-01\",\r\n"
				+ "        \"checkout\" : \"2022-02-02\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}")
		.contentType(ContentType.JSON)
		.when()
		.post()
		.then().statusCode(200);
	}
}
