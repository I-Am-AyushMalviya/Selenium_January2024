package com.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import com.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css = ".ng-star-inserted td:nth-child(3)")
	List<WebElement> historyPageProductName;
	
	public Boolean validatingProductSelection(String name) {
		Boolean match = false;
		for(WebElement prod : historyPageProductName) {
			if(prod.getText().equalsIgnoreCase(name)) {
				match = true;
				break;
			}
		}
		return match;
	}
}
