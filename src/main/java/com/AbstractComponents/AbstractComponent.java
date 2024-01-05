package com.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.PageObject.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(css = "button[routerlink*='dashboard/cart']")
	WebElement cartpage;
	
	@FindBy(css = "button[routerlink*='myorder']")
	WebElement orderPage;
	
	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForElementToInvisible(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(findBy));
	}
	public void goToCartPage() {
		cartpage.click();
	}
	public void scrollWindow() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1000)");
	}
	public void elementToBeClickable(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	public void ActionsMethod(WebElement location, String value) {
		Actions a = new Actions(driver);
		a.sendKeys(location, value).build().perform();
	}
	public OrderPage goToHistoryPage() {
		orderPage.click();
		return new OrderPage(driver);
		
	}
}
