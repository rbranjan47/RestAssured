package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class postBookingsTest {

	@Test
	public void postBookingMethod() {
		//Build Request
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.log().all();
		requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
		requestSpecification.baseUri("booking");
		
		//passing body
		requestSpecification.body("{\r\n"
				+ "    \"firstname\" : \"Scott\",\r\n"
				+ "    \"lastname\" : \"Ledbetter\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2023-01-01\",\r\n"
				+ "        \"checkout\" : \"2024-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}");
		
		requestSpecification.contentType(ContentType.JSON);
		//requestSpecification.contentType("application/json");
		//requestSpecification.accept("application/json");
		
		//hit request
		Response responseHits = requestSpecification.post();
		
		//validate request
		ValidatableResponse validatableResponses =  responseHits.then();
		validatableResponses.statusCode(200);
	}
}
