package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.config.HeaderConfig;
import io.restassured.config.RestAssuredConfig;

/*
 * NOTE: execpt and content-type headers cannot be merged, these can only overidden
 */
public class overrideHeaderValues {

	@Test(priority = 1)
	public void overrifHeadersValuesDefault() {
		RestAssured.given().log().all()
		.header("Header1","value1")
		/*
		 * here, output will be:
		 * Header1=value1
				Header1=value2
				Header2=value2
				Header2=value3
				Header2=value4
				Header2=value5
		   
		   but, Header1 and Header2 initialize each time while assigning value, to override all value in same
		 */
		.header("Header2","value1", "value2", "value3", "value4", "value5")
		.when().get();
	}
	
	@Test(priority = 2)
	public void overrifHeadersValuesActual() {
		RestAssured.given()
		//config method > Creating Restassured config for header 
		.config(RestAssuredConfig.config()
				.headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("Header1", "Header2")))
		//if 2 config files are there, only latest config
		.config(RestAssuredConfig.config()
				.headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("Header1")))
		.header("Header1","value1")
		.header("Header1","value2")
		//header1 will have final overidden value, value2
		.header("Header2","value1", "value2", "value3", "value4", "value5")
		//header2 will have final overidden value, value5
		.log().all()
		.when().get();
	}
}
