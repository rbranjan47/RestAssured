package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class postBookingMethodChaining {

	@Test
	public void putBookingTestMethodChaining() {
		// Build Request
		RestAssured
		.given()
		.log()
		.all()
		.baseUri("https://restful-booker.herokuapp.com/")
		.basePath("booking").body("{\r\n" + "    \"firstname\" : \"Scott\",\r\n"
				+ "    \"lastname\" : \"Ledbetter\",\r\n" + "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2023-01-01\",\r\n" + "        \"checkout\" : \"2024-01-01\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")

		.contentType(ContentType.JSON)
		// requestSpecification.contentType("application/json");
		// requestSpecification.accept("application/json");

		// hit request
		.post()

		// validate request
		.then().log().all().statusCode(200);
	}
}
