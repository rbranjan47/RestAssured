package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class deleteBookings {
	@Test
	public void deleteBookingTest() {
		 Response response =  RestAssured
		.given()
		.log()
		.all()
		.baseUri("https://restful-booker.herokuapp.com/")
		.basePath("booking/{bookingId}")
		.pathParam("bookingId", 1617)
		.header("Content-Type", "application/json")
		.header("Accept","application/json")
		.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
		//.header("Cookie","token=abc123")
		.when()
		.delete();
		 
		 System.out.println("Status Code:" + response.statusCode());
		 System.out.println("Response Message: " + response.asPrettyString());
	}
}
