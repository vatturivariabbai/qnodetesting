package automation;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Pause;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.pages.Shoppingcart;

import basefunctionalities.base;

public class CartTest extends base {
	
	public CartTest() {
		super();
	}
	
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
	
	
	
	@Test(priority=1)
	public void verifyAdddingMacbookfromhomepage() {
		Shoppingcart Sc = new Shoppingcart(driver);
		Sc.addMacbooktoCart();
		Sc.Opencartpage();
		String expectedresult = "MacBook";
		Assert.assertTrue(Sc.CheckforMacinCart().contains(expectedresult));		
	}
	
	@Test(priority=2)
	public void verifyIncreasingQuantitywithinCart() {
		Shoppingcart Sc = new Shoppingcart(driver);
		Sc.addMacbooktoCart();
		Sc.Opencartpage();
		Sc.ClickandincreaseQuanity();
		Sc.Updatethequantity();
		String Expectedmessage = "Success: You have modified your shopping cart!";
		Assert.assertTrue(Sc.Checkforquantityupdate().contains(Expectedmessage));
	}
	
	@Test(priority=3)
	public void VerifyEnteringInvalidCoupon() {
		Shoppingcart Sc = new Shoppingcart(driver);
		Sc.addMacbooktoCart();
		Sc.Opencartpage();
		Sc.ExpandandEnterCouponcode();
		String exprectederror = "Warning: Coupon is either invalid, expired or reached its usage limit!";
		Assert.assertTrue(Sc.CheckforinvalidcouponError().contains(exprectederror));
	}
	
	@Test(priority=4)
	public void VerifyCheckoutwithoutLogin() {
		Shoppingcart Sc = new Shoppingcart(driver);
		Sc.addMacbooktoCart();
		Sc.Opencartpage();
		Sc.ClickonCheckout();
		System.out.println(Sc.Checkfornewcustomercheckout());
		String expectedmesssage2 = "Step 1: Checkout Options";
		Assert.assertTrue(Sc.Checkfornewcustomercheckout().contains(expectedmesssage2));
	}
	
	@Test(priority=5)
	public void VerifyabortCartpage() {
		Shoppingcart Sc = new Shoppingcart(driver);
		Sc.addMacbooktoCart();
		Sc.Opencartpage();
		Sc.ClickonContinueshopping();
		String expectedhomepagemsg= "Featured";
		Assert.assertTrue(Sc.CheckforFeaturedtext().contains(expectedhomepagemsg));
	}
		

}
