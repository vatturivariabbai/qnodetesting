package automation;

import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.pages.Homepage;
import com.qa.pages.Loginpage;
import basefunctionalities.base;


public class LoginTest extends base {
	
	Loginpage loginpage;
	
	public LoginTest() {
		super();
	}
	
	WebDriver driver;
	
	@BeforeMethod
	public void startprocess() {
		driver = browserinitate(prop.getProperty("browser"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));
	
		Homepage homepage = new Homepage(driver);
		homepage.clickonmyaccount();
		loginpage = homepage.loginintoaccount();
	}
	
	@AfterMethod
	public void endprocess() {
		driver.quit();
	}
 
	@Test(priority=1, dataProvider="supplydata" )
public void verifywithlogincredentials(String email, String password) {
		
		loginpage.enteremailaddress(email);
		loginpage.enterpasswordtext(password);
		loginpage.clickonloginbutton();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
	}
	
	
	@Test(priority=2)
public void verifywithinvalidlogincredentials() {
		
		loginpage.enteremailaddress(base.generateemailwithtimestamp());
		loginpage.enterpasswordtext("12344");
		loginpage.clickonloginbutton();
		String expectedmessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(loginpage.checkforinvalidcredentialstext().contains(expectedmessage));
	}
	
	
	@Test(priority=3 )
	public void verifywithvalidemailidandinvalidpassword() {
		
		loginpage.enteremailaddress("demouser1@gmail.com");
		loginpage.enterpasswordtext("12345jk9");
		loginpage.clickonloginbutton();
		String expectedmessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(loginpage.checkforinvalidcredentialstext().contains(expectedmessage));
	}
	
	@DataProvider
	public Object[][] supplydata() {
		Object[][] data = {{"demousermalay@gmail.com","12345"},{"demouser1@gmail1.com","12345"}};
		//Object[][] data = base.getTestDataFromExcelSheet("login");
		return data; 
	}
	
	
	
}
