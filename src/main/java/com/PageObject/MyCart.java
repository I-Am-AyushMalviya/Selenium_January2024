package com.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AbstractComponents.AbstractComponent;

public class MyCart extends AbstractComponent{
	
	WebDriver driver;
	public MyCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProduct;
	
	@FindBy(css = ".totalRow button")
	WebElement cartButton;
	
	By cartButtonBy = By.cssSelector(".totalRow button");
	
	public List<WebElement> getListOfOrderPageProducts() throws InterruptedException {
		Thread.sleep(2000L);
		return cartProduct;
	}
	public boolean checkNamePresence(String productname) throws InterruptedException {
		Boolean match = false;
		for(WebElement cart : getListOfOrderPageProducts()) {
			if(cart.getText().equalsIgnoreCase(productname)) {
				match = true;
			}
		}
		return match;
	}
	public CheckOutPage clickingCartButton() throws InterruptedException {
		scrollWindow();
		elementToBeClickable(cartButtonBy);
		Thread.sleep(2000L);
		cartButton.click();
		return new CheckOutPage(driver);
	}
}
