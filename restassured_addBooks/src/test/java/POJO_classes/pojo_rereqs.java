package POJO_classes;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class pojo_rereqs 
{
	@Test
	public void rereqs_getUsers()
	{
		RestAssured.baseURI = "https://reqres.in";
		
		//expect default parser, explictily tell it is of JSON type
		getUsers response = given().log().all().queryParam("page", "2").expect().defaultParser(Parser.JSON).when()
				           .get("/api/users").as(getUsers.class);
		System.out.println(response.getPage());
		System.out.println(response.getPer_page());
		System.out.println(response.getSupport());
		System.out.println(response.getData());
		
		//email of 0th Data
		System.out.println(response.getData().get(1).getEmail());
		
		//photo url of all users in data
		int user_count = response.getData().size();
		for (int i=0; i < user_count; i++)
		{
			int id=i+1;
			System.out.println("User at "+id+" place, Name :"+response.getData().get(i).getFirst_name()+" "
			+response.getData().get(i).getLast_name()+" & Email :"+response.getData().get(i).getEmail()+" & Id :"+response.getData().get(i).getId()+" "
					+" & Avatar :"+response.getData().get(i).getAvatar());
		}
		
		//Checking conditions
		List<data_detail> dataDetails = response.getData();
		for(int i=0; i<dataDetails.size();i++)
		{
			if(dataDetails.get(i).getFirst_name().equalsIgnoreCase("Michael"))
			{
				System.out.println("User Matched having Id :"+dataDetails.get(i).getId());
			}
		}
		//Sorting in the form of Array List
		ArrayList<String> arrayDetails = new ArrayList<String>();
		
		List<data_detail> dataDetail = response.getData();
		for (int k=0; k<dataDetail.size(); k++)
		{
			arrayDetails.add(dataDetail.get(k).getFirst_name());
		}
		
		System.out.println(arrayDetails);
	}
}
