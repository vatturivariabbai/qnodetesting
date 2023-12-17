package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage {
	
	WebDriver driver;
	
	//objects
	@FindBy(id = "input-email")
	private WebElement enteremail;
	
	@FindBy(name = "password")
	private WebElement enterpassword;
	
	@FindBy(xpath = "//input[@type='submit']")
	private WebElement loginbutton;
	
	@FindBy (xpath = "//div[contains(@class, 'alert-dismissible')]")
	private WebElement textforinvalidemail;
	
	public Loginpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//actions
	
	public void enteremailaddress(String emailText) {
		enteremail.sendKeys(emailText);
	}
	
	public void enterpasswordtext(String passwordText) {
		enterpassword.sendKeys(passwordText);
	}
	
	public void clickonloginbutton() {
		loginbutton.click();
	}
	
	public String checkforinvalidcredentialstext() {
		String warningtext = textforinvalidemail.getText();
		return warningtext;
	}
}
