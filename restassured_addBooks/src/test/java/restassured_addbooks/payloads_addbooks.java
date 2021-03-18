package restassured_addbooks;

public class payloads_addbooks 
{
	public static String body_addbooks(String isbn, String asile) 
	{
		String Body= "{\r\n"
				+ "\"name\":\"Cypress with JavaScript\",\r\n"
				+ "\"isbn\":\""+isbn+"\",\r\n"
				+ "\"aisle\":\""+asile+"\",\r\n"
				+ "\"author\":\"John ford\"\r\n"
				+ "}\r\n"
				+ "";
		
		return Body;
	}
	
	public static String body_deletebooks(String id)
	{
		String delete = "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ " \r\n"
				+ "} ";
		return delete;
	}
}
