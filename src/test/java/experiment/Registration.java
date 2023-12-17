package experiment;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.Homepage;
import com.qa.pages.Registrationpage;

import basefunctionalities.base;

public class Registration extends base {
	
	Registrationpage Rgpage;
	
	public Registration() {
		super();
	}
	
	WebDriver driver;
	
	
	@BeforeMethod
	public void start() {
		driver = browserinitate(prop.getProperty("browser"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		Homepage homepage = new Homepage(driver);
		homepage.clickonmyaccount();
		Rgpage = homepage.clickonregistration();
	}
	
	@AfterMethod
	public void end() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void registerwithalldetails(){
		
		Rgpage.enterfirstname(prop.getProperty("firstname"));
		Rgpage.enterlastname(prop.getProperty("lastname"));
		Rgpage.enteremailid("uday"+generatetimestamp()+"@gmail.com");
		Rgpage.entertelephonenum(prop.getProperty("mobilenum"));
		Rgpage.enterpassword("123456");
		Rgpage.confirmpassword("123456");
		Rgpage.agreeprivacypolicy();
		Rgpage.clickoncontinuebutton();
		String expectedmessage = "Your Account Has Been Created!";
		Assert.assertTrue(Rgpage.checkforsuccessfulregistration().contains(expectedmessage));
	}
	
	@Test(priority = 2)
	public void registerwithsubscription() {
		
		Rgpage.enterfirstname(prop.getProperty("firstname"));
		Rgpage.enterlastname(prop.getProperty("lastname"));
		Rgpage.enteremailid("uday"+generatetimestamp()+"@gmail.com");
		Rgpage.entertelephonenum(prop.getProperty("mobilenum"));
		Rgpage.enterpassword("123456");
		Rgpage.confirmpassword("123456");
		Rgpage.enablenewsletter();
		Rgpage.agreeprivacypolicy();
		Rgpage.clickoncontinuebutton();
		String expectedmessage = "Your Account Has Been Created!";
		Assert.assertTrue(Rgpage.checkforsuccessfulregistration().contains(expectedmessage));
	}
	
	@Test(priority=3)
	public void verifyregistrationwithnullfirstname() {
		
		Rgpage.enterlastname(prop.getProperty("lastname"));
		Rgpage.enteremailid("uday"+generatetimestamp()+"@gmail.com");
		Rgpage.entertelephonenum(prop.getProperty("mobilenum"));
		Rgpage.enterpassword("123456");
		Rgpage.confirmpassword("123456");
		Rgpage.agreeprivacypolicy();
		Rgpage.clickoncontinuebutton();
		String expectednullmessage = "First Name must be between 1 and 32 characters!";
		Assert.assertTrue(Rgpage.checkfornullfirstname().contains(expectednullmessage));
	}
	
	@Test(priority=4)
	public void verifywithnulllemail() {

		Rgpage.enterfirstname(prop.getProperty("firstname"));
		Rgpage.enterlastname(prop.getProperty("lastname"));
		Rgpage.entertelephonenum(prop.getProperty("mobilenum"));
		Rgpage.enterpassword("123456");
		Rgpage.confirmpassword("123456");
		Rgpage.agreeprivacypolicy();
		Rgpage.clickoncontinuebutton();
		String expectednullemailmessage = "E-Mail Address does not appear to be valid!";
		Assert.assertTrue(Rgpage.checkfornullemail().contains(expectednullemailmessage));	
	}
	


	public String generatetimestamp() {
		Date date = new Date();
		return date.toString().replace(" ","_").replace(":","_");
	}
	
}


