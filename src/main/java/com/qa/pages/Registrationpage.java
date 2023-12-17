package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registrationpage {

	WebDriver driver;
	
	@FindBy(name="firstname")
	private WebElement firstnamefield;
	
	@FindBy(name="lastname")
	private WebElement lastnamefield;
	
	@FindBy(name="email")
	private WebElement emailfield;
	
	@FindBy(name="telephone")
	private WebElement telephonefield;
	
	@FindBy(name="password")
	private WebElement passwordfield;

	@FindBy(name="confirm")
	private WebElement confirmpasswordfield;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newslettersubscription;
	
	@FindBy(name="agree")
	private WebElement privacypolicyfield;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueregistrationbutton;
	
	@FindBy(xpath="//div[@id='content']")
	private WebElement textforsuccessfulregistration;
	
	@FindBy(xpath="//*[@id=\"account\"]/div[2]/div/div")
	private WebElement nullfirstname;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement nullemail;
	
	public Registrationpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//actions
	
	public void enterfirstname(String firstname) {
			firstnamefield.sendKeys(firstname);
	}
	
	public void enterlastname(String lastname) {
		lastnamefield.sendKeys(lastname);
	}
	
	public void enteremailid(String emailid) {
		emailfield.sendKeys(emailid);
	}
	
	public void entertelephonenum(String telnum) {
		telephonefield.sendKeys(telnum);
	}

	public void enterpassword(String password) {
		passwordfield.sendKeys(password);
	}

	public void confirmpassword(String samepassword) {
		confirmpasswordfield.sendKeys(samepassword);
	}
	
	public void enablenewsletter() {
		newslettersubscription.click();
	}

	public void agreeprivacypolicy() {
		privacypolicyfield.click();
	}
	
	public void clickoncontinuebutton() {
		continueregistrationbutton.click();
	}
	
	public String checkforsuccessfulregistration() {
		String successmessage = textforsuccessfulregistration.getText();
		return successmessage;
	}
	
	public String checkfornullfirstname() {
		String nullmessage = nullfirstname.getText();
		return nullmessage;
	}
	
	public String checkfornullemail() {
		String nullemailmessage = nullemail.getText();
		return nullemailmessage;
	}


}
