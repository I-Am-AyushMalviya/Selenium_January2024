package com.PageObject;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "input[placeholder*='Country']")
	WebElement countrylocator;
	
	@FindBy(css = ".list-group-item")
	List<WebElement> countries;
	
	@FindBy(css = ".action__submit")
	WebElement placeOrderButton;
	
	public void enteringCountry(String countryname ) throws InterruptedException {
		ActionsMethod(countrylocator,countryname);
		Thread.sleep(3000L);
	}
	public void selectingCountry(String countryname) {
		for(WebElement count : countries) {
			if(count.getText().equalsIgnoreCase(countryname)) {
				count.click();
				break;
			}
		}
	}
	public ConfirmationPage placeOrderButtonClick() {
		placeOrderButton.click();
		return new ConfirmationPage(driver);
	}
}
