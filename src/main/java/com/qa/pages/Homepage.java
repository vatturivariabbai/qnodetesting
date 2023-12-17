package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Homepage {
	WebDriver driver;
	
	//objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myaccountdropmenu;
	
	@FindBy(linkText="Login")
	private WebElement loginoption;
	
	@FindBy(name="agree")
	private WebElement agreebutton;
	
	@FindBy(linkText="Register")
	private WebElement Registration;
	
	
	public Homepage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//actions
	
	public void clickonmyaccount() {
		myaccountdropmenu.click();
	}
	
	public Loginpage loginintoaccount() {
		loginoption.click();
		return new Loginpage(driver);
	}
	
	public void clickonagreebutton() {
		agreebutton.click();
	}
	
	public Registrationpage clickonregistration() {
		Registration.click();
		return new Registrationpage(driver);
	}

}
