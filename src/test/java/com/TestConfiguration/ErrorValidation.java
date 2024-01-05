package com.TestConfiguration;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.PageObject.MyCart;
import com.PageObject.ProductCatalogue;
import com.TestComponents.BaseTest;
import com.TestComponents.Retry;

public class ErrorValidation extends BaseTest{
	@Test(groups = {"ErrorValidationGroup"},retryAnalyzer = Retry.class)
	public void LoginErrorValidationTest() throws InterruptedException, IOException{

		landingPage.login("aayushi@rahul.com","Bhagwan@12345");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());	
	}
	@Test
	public void ProductErrorValidationTest() throws InterruptedException {
		String productnameOne = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.login("aayush@rahul.com","Bhagwan@12345");
		List<WebElement> products = productCatalogue.getProductList();
		MyCart mycart = productCatalogue.addProductTocart(productnameOne);
		Thread.sleep(2000L);
		mycart.goToCartPage();
		Boolean match = mycart.checkNamePresence("ZARA COAT !@#");
		Assert.assertFalse(match);
	}
}
