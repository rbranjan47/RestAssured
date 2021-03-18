package restassured.restassured_demo;

import org.testng.Assert;

import files.Payload_Complex;
import io.restassured.path.json.JsonPath;

public class complexParserJson 
{
	public static void main(String[] args)
	{
		JsonPath js=new JsonPath(Payload_Complex.payload_com());
		//Courses Count
		int size_course = js.getInt("courses.size()");
		System.out.println("Size of the Course :"+size_course);
		
		
		
		//purchase amount
		long amount = js.getLong("dashboard.purchaseAmount");
		System.out.println("Purchase amount :"+amount);
		
		//title of the first course
		String title_first=js.getString("courses[0].title");
		System.out.println("First course title :"+title_first);
		
		//price of all the courses and their price
		System.out.println("First course title : "+js.getString("courses[0].title")+" & price :"+js.getInt("courses[0].price")+ " & Copies :"
		+js.getLong("courses[0].copies"));
		
		System.out.println("Second course title : "+js.getString("courses[1].title")+" & price :"+js.getInt("courses[1].price")+ " & Copies :"
				+js.getLong("courses[1].copies"));
		
		System.out.println("Third course title : "+js.getString("courses[2].title")+" & price :"+js.getInt("courses[2].price")+ " & Copies :"
				+js.getLong("courses[2].copies"));
		
		
		
		/* We can also print courses details from "For" Loop */  System.out.println("Using for loop ");
		int count=js.getInt("courses.size()");
		for (int i=0;i<count;i++)
		{
			System.out.println(i+" Course name :"+js.getString("courses["+i+"].title")+" & price :"+js.getInt("courses["+i+"].price")+ " & Copies :"
		+js.getLong("courses["+i+"].copies"));
		}
		
		
		
		//Calculating the number of copies sold 
		int copy_count=js.getInt("courses.size()");
		int value=0;
		for (int i=0;i<copy_count;i++)
		{
			int value_next=js.getInt("courses["+i+"].copies");
			value=value+value_next;
		}
		
		System.out.println("Total Copies :" + value);
		
		//No. of copies sold by the RPA Using Logical conditions
		for (int i=0;i<count;i++)
		{
		String name_rpa = js.getString("courses["+i+"].title");
		System.out.println(name_rpa);
		if (name_rpa.equalsIgnoreCase("RPA"))
		{
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			System.out.println("Price of RPA "+price+" and copies "+ copies );
			break;
		}
		}
		
		
		//verifying the sum of the prices of the Books
		int total=130;
		int sum=js.getInt("courses[0].price")+js.getInt("courses[1].price")+js.getInt("courses[1].price");
		Assert.assertEquals(total, sum);
		
	}
}
