package com.PageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@id='userEmail']")
	WebElement username;
	
	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement password;
	
	@FindBy(xpath = "//input[@id='login']")
	WebElement login;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	public ProductCatalogue login(String username , String password) {
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public String getErrorMessage() {
		return errorMessage.getText();
		
		}
	}


