package automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.Searchpage;

import basefunctionalities.base;

public class SearchTest extends base {
	

	WebDriver driver;
	
	@BeforeMethod
	public void start() {
		driver = browserinitate(prop.getProperty("browser"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));
	}
	
	@AfterMethod
	public void end() {
		driver.quit();
	}
	
	@Test(priority =1)
	public void searchforhplaptop() {
		
		Searchpage sp = new Searchpage(driver);
		
		sp.EnterProductinSearchbar("HP");
		sp.SearchbuttonClick();
		String expectedmessage = "HP LP3065";
		Assert.assertTrue(sp.SearchresultvalidationHP().equals(expectedmessage));
	}
	@Test(priority=2)
	public void searchfornonexistingitem() {

		Searchpage sp = new Searchpage(driver);
		
		sp.EnterProductinSearchbar("Car");
		sp.SearchbuttonClick();
		String expectedsearchresult = "There is no product that matches the search criteria.";
		Assert.assertEquals(sp.SearchresultvalidationCar(), expectedsearchresult);
	}

	
	
}
