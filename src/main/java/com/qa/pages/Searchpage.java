package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Searchpage {
	WebDriver driver;
	
	@FindBy(name="search")
	private WebElement searchbar;
	
	@FindBy(xpath="//button[@type='button'][@class='btn btn-default btn-lg']")
	private WebElement searchbutton;
	
	@FindBy(linkText="HP LP3065")
	private WebElement searchresultHP;
	
	@FindBy(xpath="//*[@id=\"content\"]/p[2]")
	private WebElement searchresultCar;
	
	public Searchpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//actions
	
	public void EnterProductinSearchbar(String productname) {
		searchbar.sendKeys(productname);
	}
	
	public void SearchbuttonClick() {
		searchbutton.click();
		
	}
	
	public String SearchresultvalidationHP() {
		String searchedtext = searchresultHP.getText();
		return searchedtext;
	}
	
	public String SearchresultvalidationCar() {
		String searchedtext = searchresultCar.getText();
		return searchedtext;
	}
}
