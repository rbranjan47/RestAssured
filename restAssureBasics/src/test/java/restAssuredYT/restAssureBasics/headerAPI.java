package restAssuredYT.restAssureBasics;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;

/**
 * Header: 
 * > Key value pairs 
 * > Metadata associated with request and response of API 
 * > Contain Authorization, cookies, body type, proxies, additional data for API
 * > header() and headers() in RestAssured
 * 
 * 
 * Headers:
 * > multiple header pass
 */
public class headerAPI {
	@Test(priority = 1)
	public void passHeaderAPITest1() {
		RestAssured.given().log().all()
		.header("Header1","value1")
		.header("Header2","value1", "value2", "value3", "value4", "value5")
		.when().get();
	}
	
	@Test(priority = 2)
	public void passHeaderAPITest2() {
	 Header headerss = new Header("Header2", "value6");
		RestAssured.given().log().all()
		.header("Header1","value1")
		//we can pass value differently
		.header("Header2","value1")
		.header("Header2","value2")
		.header("Header2","value3")
		.header("Header2","value4")
		.header("Header2","value5")
		//OVERRIDE PASSING HEADER, CREATING OBJECT OF HEADER CLASS
		.header(headerss)
		.when().get();
	}
	
	
	//HEADERS
	@Test(priority = 3)
	public void pass_Headers_APITest3() {
		     RestAssured.given().log().all()
			//Headers, multiple header pass
			.headers("Header1","value1","Header2","value2","Header1","value2","Header2","value3")
			.when().get();
		}
	
	@Test(priority = 4)
	public void pass_HeadersHashapAPITest4() {
		 //we can also pass hashMap, by storing into it
	 	Map<String, String> haedersMapping = new HashMap<>();
		haedersMapping.put("Header11","value11");
		haedersMapping.put("Header21","value221");
		haedersMapping.put("Header22","value222");
		haedersMapping.put("Header23","value223");
		haedersMapping.put("Header24","value224");
		
		 RestAssured.given().log().all()
			//Headers, multiple header pass
			.headers(haedersMapping)
			.when().get();
	}
	
	
}
