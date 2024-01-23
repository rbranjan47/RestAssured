package restAssuredYT.restAssureBasics;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class responseSpecificationsBooking {

	RequestSpecification requestSpecifications;
	ResponseSpecification responseSepcification;

	@BeforeClass
	public void requestSpecificationSetup() {
		requestSpecifications = RestAssured.given(); // RequestSpecification is an Interface

		// adding logger to get all requesting message
		requestSpecifications.log().all();
		requestSpecifications.baseUri("https://restful-booker.herokuapp.com/");
		requestSpecifications.basePath("booking");
		requestSpecifications.contentType(ContentType.JSON); // instead of typing 'application/json'
	}

	@BeforeClass
	public void responseBooking() {
		responseSepcification = RestAssured.expect();
		responseSepcification.statusCode(200);
		responseSepcification.contentType(ContentType.JSON);
		responseSepcification.time(Matchers.lessThan(5000L));
	}

	@Test
	public void requestSpecificationCreateBooking() {
		RestAssured.given().spec(requestSpecifications)
				.body("{\r\n" + "    \"firstname\" : \"Rabi\",\r\n" + "    \"lastname\" : \"Kumar\",\r\n"
						+ "    \"totalprice\" : 799,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2023-01-01\",\r\n"
						+ "        \"checkout\" : \"2023-02-02\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}");

		// hit request and get response
		requestSpecifications.post(); // Response is an Interface

		// validate response
		// ValidatableResponse responseValidate = responses.then();
		// responseValidate.statusCode(200);

		// USING METHOD CHAINING
		RestAssured.given().log().all().baseUri("https://restful-booker.herokuapp.com/").basePath("booking")
				.body("{\r\n" + "    \"firstname\" : \"Tom\",\r\n" + "    \"lastname\" : \"Smith\",\r\n"
						+ "    \"totalprice\" : 199,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2022-01-01\",\r\n"
						+ "        \"checkout\" : \"2022-02-02\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				.post().then().spec(responseSepcification);
		// .statusCode(200);
	}

	@Test
	public void requestSpecificationUpdateBooking() {

		RestAssured.given().spec(requestSpecifications)
				.body("{\r\n" + "    \"firstname\" : \"Rabi\",\r\n" + "    \"lastname\" : \"Kumar\",\r\n"
						+ "    \"totalprice\" : 799,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2023-01-01\",\r\n"
						+ "        \"checkout\" : \"2023-02-02\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}");

		// hit request and get response
		requestSpecifications.post().then().spec(responseSepcification); // Response is an Interface

		// validate response
		// ValidatableResponse responseValidate = responses.then();
		// responseValidate.statusCode(200);

		// USING METHOD CHAINING
		RestAssured.given().log().all().baseUri("https://restful-booker.herokuapp.com/").basePath("booking")
				.body("{\r\n" + "    \"firstname\" : \"Tom\",\r\n" + "    \"lastname\" : \"Smith\",\r\n"
						+ "    \"totalprice\" : 199,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2022-01-01\",\r\n"
						+ "        \"checkout\" : \"2022-02-02\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				.contentType(ContentType.JSON).post().then().spec(responseSepcification);
	}
}
