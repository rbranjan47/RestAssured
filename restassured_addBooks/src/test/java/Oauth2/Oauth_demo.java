package Oauth2;

import static io.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Oauth_demo 
{
	@Test
	public void oauth_demo() throws InterruptedException
	{
		//getting the Authorization code to pass to post data in the another server
		//Here,we are using the selenium concept to get the Auth. code
		String username = "functiontest871@gmail.com";
		String password = "test1234@#";
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		//getting the website
		driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?scope="
				+ "https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url="
				+ "https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id"
				+ "=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type="
				+ "code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&State=ranjan&flowName=GeneralOAuthFlow");
		WebElement email_field = driver.findElement(By.id("identifierId"));
		email_field.clear();
		email_field.sendKeys(username);
		Thread.sleep(5000);
		driver.findElement(By.className("VfPpkd-RLmnJb")).click();
		WebElement password_field = driver.findElement(By.xpath("//input[@name='password']"));
		password_field.clear();
		password_field.sendKeys(password);
		Thread.sleep(5000);
		driver.findElement(By.className("VfPpkd-RLmnJb")).click();
		//putting in sleep so that we can get proper URL
		String URL = driver.getCurrentUrl();
		//checking the URL 
		System.out.println(URL);
		driver.close();
		//we need to Split the URL as we need only "code" from the given URL
		String partial_url = URL.split("code=")[1];
		System.out.println(partial_url);
		String scope_url = partial_url.split("&scope")[0];
		System.out.println(scope_url);
		
		
		//Getting Access Token to pass in th login functionality
		String accesstoken_response =  given().urlEncodingEnabled(false)
		.queryParams("code", scope_url)
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("State", "ranjan")
		.queryParams("grant_type","Authorization code")
		.when().log().all()
		.post("https://accounts.google.com/o/oauth2/v2/auth").asString();
		
		JsonPath js = new JsonPath(accesstoken_response);
		String accesstoken =js.getString("accessToken");
		//printing for checking it
		System.out.println(accesstoken);
		
		//storing the respose value in the String 
		String response = given().queryParam("accessToken", accesstoken).when().log().all()
				.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
		
		
	}
}
