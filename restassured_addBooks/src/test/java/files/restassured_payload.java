package files;

public class restassured_payload 
{
	public static String addplace() 
	{
		return "{\r\n" + "  \"location\": {\r\n" + "    \"lat\": -38.383494,\r\n" + "    \"lng\": 33.427362\r\n"
				+ "  },\r\n" + "  \"accuracy\": 50,\r\n" + "  \"name\": \"Frontline house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n" + "  \"types\": [\r\n" + "    \"shoe park\",\r\n"
				+ "    \"shop\"\r\n" + "  ],\r\n" + "  \"website\": \"http://google.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n" + "}";
	}
	
	public static String  addbook(String isbn, String aisle)
	{
		String addbook_body= "{\r\n"
				+ "\"name\":\"Cypress with JavaScript\",\r\n"
				+ "\"isbn\":\"gfh\",\r\n"
				+ "\""+aisle+"\":\""+isbn+"\",\r\n"
				+ "\"author\":\"John ford\"\r\n"
				+ "}\r\n"
				+ "";
		
		return addbook_body;
	}
}
