package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

/*
 * 3 steps:
 *  - Build Request
 *  - Hit Request and get Response
 *  - Validate Response
 */
public class createBookingPOST {
	
	@Test
	public void createBookingRestfulBookers() {
		//build request
		RequestSpecification requestSpecifications = RestAssured.given();   //RequestSpecification is an Interface
		
		//adding logger to get all requesting message
		requestSpecifications = requestSpecifications.log().all();   
		
		 
		requestSpecifications.baseUri("https://restful-booker.herokuapp.com/");
		requestSpecifications.basePath("booking");
		
		requestSpecifications.body("{\r\n"
				+ "    \"firstname\" : \"Rabi\",\r\n"
				+ "    \"lastname\" : \"Kumar\",\r\n"
				+ "    \"totalprice\" : 799,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2023-01-01\",\r\n"
				+ "        \"checkout\" : \"2023-02-02\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}");
		requestSpecifications.contentType(ContentType.JSON);  //instead of typing 'application/json' 
		
		//hit request and get response
		Response responses = requestSpecifications.post();   // Response is an Interface
		
		//validate response
		ValidatableResponse responseValidate = responses.then();
		responseValidate.statusCode(200);
		
		//USING METHOD CHAINING
		RestAssured.given().log().all().baseUri("https://restful-booker.herokuapp.com/").basePath("booking").body("{\r\n"
				+ "    \"firstname\" : \"Tom\",\r\n"
				+ "    \"lastname\" : \"Smith\",\r\n"
				+ "    \"totalprice\" : 199,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2022-01-01\",\r\n"
				+ "        \"checkout\" : \"2022-02-02\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}").contentType(ContentType.JSON).post().then().statusCode(200);
	}
}
