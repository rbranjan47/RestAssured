package files;

public class jira_BODY 
{
	//create issue Body
	public static String createBody()
	{
		String Body = "{\r\n"
				+ "    \"fields\" :\r\n"
				+ "    {\r\n"
				+ "        \"project\" :\r\n"
				+ "        {\r\n"
				+ "            \"key\" : \"REST1234\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\" : \"Profile Image is not displaying Correctly\",\r\n"
				+ "        \"description\" : \"After Uploading the Profile Image, it is displaying as blur\",\r\n"
				+ "        \"issuetype\" : \r\n"
				+ "        {\r\n"
				+ "            \"name\" :\"Bug\"\r\n"
				+ "        },\r\n"
				+ "                \"environment\": \"QA\"\r\n"
				+ "                 \r\n"
				+ "                }\r\n"
				+ "    }\r\n"
				+ "}";
		return Body;
	}
	
	//Login session Body
	public static String loggedIN()
	{
		String loggedin = "{ \"username\": \"rbranjan47\", \"password\": \"Rabi1234@#\" }";
		return loggedin;
	}
	
	//passing the Comments Body
	public static String commentsbody()
	{
		String comments = "{\r\n"
				+ "    \"body\": \"Hey! this issue is not Reproducable from my end. Can you Please attach a Video or Image.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
		return comments;
	}
}
