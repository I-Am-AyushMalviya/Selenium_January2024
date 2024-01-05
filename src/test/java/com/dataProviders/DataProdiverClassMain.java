package com.dataProviders;

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

public class DataProdiverClassMain extends BaseTest{
	@Test(dataProvider = "dataPRoviders", groups = {"purchaseDataProvider"})
	public void dataProviderDataMethod(String username,String password,String product) throws InterruptedException {

		ProductCatalogue productCatalogue = landingPage.login(username,password);
		List<WebElement> products = productCatalogue.getProductList();
		MyCart mycart =  productCatalogue.addProductTocart(product);
		Thread.sleep(2000L);
		mycart.goToCartPage();
		Boolean match = mycart.checkNamePresence(product);
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
	public Object[][] dataPRoviders() {
		return new Object [][] {{"aayush@rahul.com","Bhagwan@12345","ZARA COAT 3"},{"shlok@rahul.com","Bhagwan@12345","IPHONE 13 PRO"}};
	}
}
