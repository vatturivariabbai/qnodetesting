package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Shoppingcart {
	
	WebDriver driver;
	
	@FindBy(xpath= "//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]")
	private WebElement addMacbook;
	
	@FindBy(xpath = "/html/body/nav/div/div[2]/ul/li[4]/a/span")
	private WebElement shoppingcartpage;
	
	@FindBy(xpath= "/html/body/div[2]/div/div/form/div/table/tbody/tr/td[2]/a")
	private WebElement mackbookproductincart;
	
	@FindBy(xpath= "//input[@value='1']")
	private WebElement quantityfieldincartpage;
	
	@FindBy(xpath= "//button[@type='submit']")
	private WebElement quantityupdatebutton;
	
	@FindBy(xpath= "//div[@class='alert alert-success alert-dismissible']")
	private WebElement successmesssageonupdate;
	
	@FindBy(xpath= "//*[@id=\"accordion\"]/div[1]/div[1]/h4/a")
	private WebElement expandcouponcode;
	
	@FindBy(xpath= "//input[@id='input-coupon']")
	private WebElement entercouponfield;
	
	@FindBy(xpath= "//input[@id='button-coupon']")
	private WebElement submitcouponbutton;

	@FindBy(xpath= "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement invalidcouponerror;
	
	@FindBy(xpath= "//*[@id=\"content\"]/div[3]/div[2]/a")
	private WebElement Checkoutbutton;
	
	@FindBy(xpath= "//*[@id=\"accordion\"]/div[1]/div[1]/h4/a")
	private WebElement newcustomertext;
	
	@FindBy(linkText= "Continue Shopping")
	private WebElement continueshoppingbutton;
	
	@FindBy(xpath = "/html/body/div[2]/div/div/h3")
	private WebElement featuredsection;
	
	
	public Shoppingcart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
// actions

	public void addMacbooktoCart() {
		addMacbook.click();
	}
	
	public void Opencartpage() {
		shoppingcartpage.click();
	}
	
	public String CheckforMacinCart() {
		String Successmessageformac = mackbookproductincart.getText();
		return Successmessageformac;
	}
	
	public void ClickandincreaseQuanity() {
		quantityfieldincartpage.click();
		quantityfieldincartpage.clear();
		quantityfieldincartpage.sendKeys("2");
	}
	
	public void Updatethequantity() {
		quantityupdatebutton.click();
	}
	
	public String Checkforquantityupdate() {
		String message = successmesssageonupdate.getText();
		return message;
	}
	
	public void ExpandandEnterCouponcode() {
		expandcouponcode.click();
		entercouponfield.sendKeys("345coupons");
		submitcouponbutton.click();
	}
	
	public String CheckforinvalidcouponError() {
		String errormessage = invalidcouponerror.getText();
		return errormessage;
	}
	
	public void ClickonCheckout() {
		Checkoutbutton.click();
	}
	
	public String Checkfornewcustomercheckout() {
		String text = newcustomertext.getText();
		return text;
	}
	
	public void ClickonContinueshopping() {
		continueshoppingbutton.click();
	}
	
	public String CheckforFeaturedtext() {
		String featuretexthomepage = featuredsection.getText();
		return featuretexthomepage;
	}
	


}
