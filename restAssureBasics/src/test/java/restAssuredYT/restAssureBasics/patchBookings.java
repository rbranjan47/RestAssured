package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

/*
 * a hhtps request, update a resource partially or completely
 * represents parts of resources need to be modified
 * not idempotent and safe
 */
public class patchBookings {

	@Test
	public void pathBookingTest() {
		RestAssured
		.given()
		.log()
		.all()
		.baseUri("https://restful-booker.herokuapp.com/")
		.basePath("booking/1")
		.header("Content-Type", "application/json")
		.header("Accept","application/json")
		.header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
		//.header("Cookie","token=abc123")
		.body("{\r\n"
				+ "    \"firstname\" : \"Scott\",\r\n"
				+ "    \"lastname\" : \"Brown\"\r\n"
				+ "}")
		.when()
		.patch()
		.then()
		.log().all()
		.assertThat()
		.statusCode(200);
	}
}
