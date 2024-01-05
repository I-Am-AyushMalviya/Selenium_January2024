package com.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	@FindBy(css = ".ng-animating")
	WebElement ngAnimating;

	By products1 = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementToAppear(products1);
		return products;
	}

	public WebElement getProductByName(String productname) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname))
				.findFirst().orElse(null);
		return prod;
	}

	public MyCart addProductTocart(String productname) {
		WebElement prod = getProductByName(productname);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElementToInvisible(ngAnimating);
        return new MyCart(driver);

	}

	public MyCart addProductToCartLoop(String productname) {
		for (WebElement productOne : getProductList()) {
			if (productOne.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname)) {
				productOne.findElement(addToCart).click();
				waitForElementToAppear(toastContainer);
				waitForElementToInvisible(ngAnimating);
				break;
			}

		}
		return  new MyCart(driver);
	}
}
