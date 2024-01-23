package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

/*PUT vs POST
 > Put a HTTP request to upgrade or create a resource
 > replace current resource with new payload resource, if not found will create another
 > Idompotent
 > 201, 200, and 204 status code
*/
public class putBookings {

	@Test
	public void putBookingTest() {
		// Build Request
				RestAssured
				.given()
				.log()
				.all()
				.baseUri("https://restful-booker.herokuapp.com/")
				.basePath("booking/1")
				.header("Content-Type", "application/json")
				.header("Accept","application/json")
				.header("Authorization","YWRtaW46cGFzc3dvcmQxMjM=")
				.body("{\r\n"
						+ "    \"firstname\" : \"James\",\r\n"
						+ "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n"
						+ "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n"
						+ "        \"checkin\" : \"2023-01-01\",\r\n"
						+ "        \"checkout\" : \"2024-01-01\"\r\n"
						+ "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
						+ "}")
				.when().put().then()
				.log().all();
	}
}
