package twilio_otp_verification;

import java.util.concurrent.TimeUnit;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

import io.github.bonigarcia.wdm.WebDriverManager;

public class otp_verification {
	public static final  String twilio_sid = "AC25c9fd3bf7dbb75af40831e9c8b67d99";
	public static final  String twilio_authentication_token = "f7560fddf71bc841cb9cfc7560dd8ad8";
	
	@Test
	public void amazonOTP_handle() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.amazon.in/");
		
		WebElement sign_up = driver.findElement(By.xpath("//a[@id='nav-link-accountList']"));
		Actions action = new Actions(driver);
		action.moveToElement(sign_up).build().perform();
		driver.findElement(By.xpath("//div[@id='nav-flyout-ya-signin']//span[@class='nav-action-inner'][normalize-space()='Sign in']")).click();
		//create a account
		driver.findElement(By.xpath("//a[contains(text(),'Create your Amazon account')]")).click();
		//firstname
		driver.findElement(By.xpath("//input[@id='ap_customer_name']")).sendKeys("sample name");
		//click on number country selection
		driver.findElement(By.xpath("//span[@id='auth-country-picker-container']")).click();
		//selecting country
		driver.findElement(By.xpath("//a[contains(text(),'United States +1')]")).click();
		//mobile number
		String mobile_number = "6094546120";
		driver.findElement(By.id("ap_phone_number")).sendKeys(mobile_number);
		//password
		driver.findElement(By.id("ap_password")).sendKeys("Rabi1234@#");
		driver.findElement(By.id("continue")).click();
		
		//GET THE OTP FROM TRILIO SITE
		Twilio.init(twilio_sid, twilio_authentication_token);
		String sms_otp = getMessage();
		System.out.println(sms_otp);
		
		String sms_otp_withouttext = sms_otp.replace("[^-?0-9]+", " ");
		System.out.println(sms_otp_withouttext);
		
		driver.findElement(By.id("auth-pv-enter-code")).sendKeys(sms_otp_withouttext);
		driver.findElement(By.id("auth-verify-button")).click();
		
	}
	
	public static String getMessage()
	{
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals("+16094546120")).map(Message::getBody).findFirst()
				.orElseThrow(IllegalStateException::new);
	}

	private static java.util.stream.Stream<Message> getMessages() 
	{
		ResourceSet<Message> messages = Message.reader(twilio_sid).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}
	
	/*@Test
	public void flipkart_handle() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		driver.get("https://www.flipkart.com/");
	}*/
}
