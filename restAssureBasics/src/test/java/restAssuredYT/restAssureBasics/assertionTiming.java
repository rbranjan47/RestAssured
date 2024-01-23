package restAssuredYT.restAssureBasics;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class assertionTiming {

	@Test
	public void assertionTimingTest() {
		// build request
		RequestSpecification requestSpecifications = RestAssured.given(); // RequestSpecification is an Interface

		// adding logger to get all requesting message
		requestSpecifications = requestSpecifications.log().all();

		requestSpecifications.baseUri("https://restful-booker.herokuapp.com/");
		requestSpecifications.basePath("booking");

		requestSpecifications.body("{\r\n" + "    \"firstname\" : \"Rabi\",\r\n" + "    \"lastname\" : \"Kumar\",\r\n"
				+ "    \"totalprice\" : 799,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2023-01-01\",\r\n" + "        \"checkout\" : \"2023-02-02\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}");
		requestSpecifications.contentType(ContentType.JSON); // instead of typing 'application/json'

		// hit request and get response
		Response responses = requestSpecifications.post(); // Response is an Interface

		// validate response
		ValidatableResponse responseValidate = responses.then();
		responseValidate.statusCode(200);

		//time
		Long responseTime = responses.time();
		System.out.println(responseTime);
		
		//EXTRACTING TIME AND ASSERTION
		responses.then().time(Matchers.lessThan(5000L));   //less than given time
		responses.then().time(Matchers.greaterThan(2000L));   // more than given time
		responses.then().time(Matchers.both(Matchers.greaterThan(1000L)).and(Matchers.lessThan(6000L)));  //between given time
		
	}
}
