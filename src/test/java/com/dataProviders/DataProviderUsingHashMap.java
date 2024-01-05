package com.dataProviders;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.PageObject.CheckOutPage;
import com.PageObject.ConfirmationPage;
import com.PageObject.MyCart;
import com.PageObject.ProductCatalogue;
import com.TestComponents.BaseTest;

public class DataProviderUsingHashMap extends BaseTest{
	@Test(dataProvider = "dataProviderHashMap")
	public void dataProviderUsingHashMap(HashMap<String,String> input) throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.login(input.get("email"),input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		MyCart mycart =  productCatalogue.addProductTocart(input.get("product"));
		Thread.sleep(2000L);
		mycart.goToCartPage();
		Boolean match = mycart.checkNamePresence(input.get("product"));
		Assert.assertTrue(match);
		Thread.sleep(5000L);
		CheckOutPage checkoutPage = mycart.clickingCartButton();
		checkoutPage.enteringCountry("Ind");
		checkoutPage.selectingCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.placeOrderButtonClick();
		String confirmationMessage = confirmationPage.gettingConfirmationMessage();
		Assert.assertEquals("THANKYOU FOR THE ORDER.",confirmationMessage);
	}
	@DataProvider
	public Object[][] dataProviderHashMap() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email","ayush@rahul.com");
		map.put("password","Bhagwan@12345");
		map.put("product","ZARA COAT 3");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email","shlok@rahul.com");
		map1.put("password","Bhagwan@12345");
		map1.put("product","ADIDAS ORIGINAL");		
		
		return new Object [][] {{map},{map1}};
	}
}
