package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class getBookingsGET {
	@Test
	public void getBookingsGETTest() {
		RequestSpecification requestSpecifications = RestAssured.given();

		requestSpecifications.baseUri("https://restful-booker.herokuapp.com/");
		requestSpecifications.basePath("booking/{id}");

		// passing booking id as parameters
		requestSpecifications.pathParam("id", 2641);

		// getting responses
		Response responses = requestSpecifications.get();

		// validating responses
		ValidatableResponse validabtleResponses = responses.then();

		// logging all requests
		validabtleResponses = responses.then().log().all();
		validabtleResponses.statusCode(200);
	}
}
