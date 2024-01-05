package com.TestConfiguration;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.PageObject.CheckOutPage;
import com.PageObject.ConfirmationPage;
import com.PageObject.MyCart;
import com.PageObject.OrderPage;
import com.PageObject.ProductCatalogue;
import com.TestComponents.BaseTest;

public class TestConfigurationMainClassFilePOM extends BaseTest{
	@Test
	public void sumbitOrderTest() throws InterruptedException, IOException{
		String productnameOne = "ZARA COAT 3";
		String productnameTwo = "IPHONE 13 PRO";
		String productnameThree = "ADIDAS ORIGINAL";
		String productCheck = "ZARA COAT 3";
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
	}
	
	@Test(dependsOnMethods = {"sumbitOrderTest"})
	public void orderHistoryPage() {
		ProductCatalogue productCatalogue = landingPage.login("aayush@rahul.com","Bhagwan@12345");
		OrderPage orderpage = productCatalogue.goToHistoryPage();
		boolean match =orderpage.validatingProductSelection("ZARA COAT 3");
		Assert.assertTrue(match);
	}

}
