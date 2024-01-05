package com.dataProviders;

import java.io.IOException;
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

public class GetDataFromJSONMain extends BaseTest{
	@Test(dataProvider = "jsonDataProvider")
	public void getDataFromJSONMain(HashMap<String,String> input) throws InterruptedException {
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
	public Object[][] jsonDataProvider() throws IOException {
		List<HashMap<String,String>> data = getDataFromJSON(System.getProperty("user.dir")+ "//src//test//java//com//dataProviders//DataProviderJSONFile.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}	
}

