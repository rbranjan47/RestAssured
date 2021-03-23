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
				+ "        \"summary\" : \"User is not able to send messages\",\r\n"
				+ "        \"description\" : \"Send button is not working when user clicks it to send message\",\r\n"
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
				+ "    \"body\": \"Hey! this issue is Blocker. I am fixing it ASAP.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
		return comments;
	}
}
