package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class pathParameterAPI {

	@Test
	public void pathParameterAPITest() {
		RestAssured.given().log().all()
		//.baseUri("https://restful-booker.herokuapp.com/")
				//.basePath("{basePath}/{bookingId}")
				.pathParam("basePath", "booking")
			//.pathParam("bookingId", 10)
			.when()
			.get("https://restful-booker.herokuapp.com/{basePath}/{bookingId}",12)
			.then().log().all();
	}
}
