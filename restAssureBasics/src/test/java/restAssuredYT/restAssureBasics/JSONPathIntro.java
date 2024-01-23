package restAssuredYT.restAssureBasics;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

/*  JSON Path Intro:
 * > similar to xpath
 * > expression to refer or uniquely identifying a node or element in JSON document
 * > RestAssured provides JSON path
 * 
 * 
 * RULE:
 * Root node > denoted by  $
 * child node > denoted by .
 * we can not use [](square bracket) to denote child node
 */
public class JSONPathIntro {
	@Test
	public void testJSONPathTest() {
		String json = "{\r\n" + "    \"firstname\" : \"James\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n" + "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n" + "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n" + "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}";

		// get json path instance
		JsonPath jsonPaths = new JsonPath(json);
		String firstName = jsonPaths.getString("firstname");
		String lastname = jsonPaths.getString("lastname");
		double totalPrice  = jsonPaths.getDouble("totalprice");
		boolean depositPaid = jsonPaths.getBoolean("depositpaid");
		Object checkInDate = jsonPaths.get("checkin");
		String checkOutDate = jsonPaths.get("checkout");
		String additionalNeeds = jsonPaths.getString("additionalneeds");
		System.out.println("First name: "+firstName + "\n" +
				"Last name: " + lastname + "\n" +
				"Total Price: "+ totalPrice + "\n" +
				"Deposit paid: " + depositPaid +"\n" +
				"checkInDate: "+ checkInDate +"\n" +
				"checkOutDate: " + checkOutDate +"\n" +
				"additionalNeeds: " + additionalNeeds
				);
		
		
		//To print root node
		System.out.println((Object)jsonPaths.get("$"));  //return JSON Objects
		//with get string
		System.out.println((Object)jsonPaths.getString("$"));  //String return Array String
		//with empty
		System.out.println((Object)jsonPaths.get());   //return JSON Objects
		//with string empty
		System.out.println((Object)jsonPaths.getString("$"));   //String return Array String
		}
}
