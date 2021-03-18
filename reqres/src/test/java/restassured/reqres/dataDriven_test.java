package restassured.reqres;

import static io.restassured.RestAssured.given;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class dataDriven_test 
{
	
	@DataProvider(name = "dataprovider")
	public Object[][] dataforPost()
	{
		
		//In this manually entering the Data Value, by creating an Object
		Object[][] data = new Object[3][5];
		data[0][0] = "Aurbindo";
		data[0][1] = "Swain";
		data[0][2] = "mechanical engineer";
		data[0][3] = 5;
		data[0][4] = 5;
		
		data[1][0] = "Ankush";
		data[1][1] = "Panda";
		data[1][2] = "buisness-men";
		data[1][3] = 6;
		data[1][4] = 6;
		
		data[2][0] = "Jasrup";
		data[2][1] = "Singh";
		data[2][2] = "self employed";
		data[2][3] = 7;
		data[2][4] = 7;
		
		return data;
	}

	@SuppressWarnings("unchecked")
	@Test(dataProvider = "dataprovider")
	public void dataDriven_post(String firstname, String lastname, String job, int id, int subjectId)
	{
		RestAssured.baseURI = "http://localhost:3000/";
		//getting the user details
		given().get("/users").then().statusCode(200).log().all();
		//getting the subject name
		given().param("name", "automation").get("/subjects").then().statusCode(200).log().all();
		
		//putting the Value by JSON Object
		JSONObject request = new JSONObject();
		request.put("firstname", firstname);
		request.put("lastname", lastname);
		request.put("job", job);
		request.put("id", id);
		request.put("subjectId", subjectId);
		
		
		given().contentType(ContentType.JSON).header("Content-Type","application/json").body(request.toJSONString()).when().post("/users").then().statusCode(200).log().all();
}
}
