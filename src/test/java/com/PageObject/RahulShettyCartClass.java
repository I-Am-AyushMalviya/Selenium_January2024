package com.PageObject;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;



public class RahulShettyCartClass {
	public static void main(String args[]) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String productnameOne = "ZARA COAT 3";
		String productnameTwo = "IPHONE 13 PRO";
		String productnameThree = "ADIDAS ORIGINAL";
		String productCheck = "ZARA COAT 3";
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.login("aayush@rahul.com","Bhagwan@12345");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductTocart(productnameOne);
		Thread.sleep(2000L);
		productCatalogue.addProductTocart(productnameTwo);
		Thread.sleep(2000L);
	    MyCart mycart = productCatalogue.addProductToCartLoop(productnameThree);
		Thread.sleep(2000L);
		mycart.goToCartPage();
		Boolean match = mycart.checkNamePresence(productCheck);
		Assert.assertTrue(match);
		Thread.sleep(5000L);
		CheckOutPage checkoutPage = mycart.clickingCartButton();
		checkoutPage.enteringCountry("Ind");
		checkoutPage.selectingCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.placeOrderButtonClick();
		String confirmationMessage = confirmationPage.gettingConfirmationMessage();
		Assert.assertEquals("THANKYOU FOR THE ORDER.",confirmationMessage);
		driver.quit();
	}

}
