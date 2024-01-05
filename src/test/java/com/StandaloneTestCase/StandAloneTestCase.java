package com.StandaloneTestCase;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTestCase {
	public static void main(String args[]) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		WebElement userName = driver.findElement(By.xpath("//input[@id='userEmail']"));
		WebElement password = driver.findElement(By.xpath("//input[@id='userPassword']"));
		WebElement login = driver.findElement(By.xpath("//input[@id='login']"));
		userName.sendKeys("aayush@rahul.com");
		password.sendKeys("Bhagwan@12345");
		login.click();
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ZARA COAT 3"))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(5000L);
		WebElement prodL = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("IPHONE 13 PRO"))
				.findFirst().orElse(null);
		prodL.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(5000L);
		for(WebElement productOne : products) {
			if(productOne.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ADIDAS ORIGINAL")) {
				productOne.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				break;
			}
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("button[routerlink*='dashboard/cart']")).click();
		Boolean match = false;
		List<WebElement> cartProduct = driver.findElements(By.cssSelector(".cartSection h3"));
		for(WebElement cart : cartProduct) {
			if(cart.getText().equalsIgnoreCase("ZARA COAT 3")) {
				match = true;
			}
		}
		Assert.assertTrue(match);
		Thread.sleep(5000L);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
		driver.findElement(By.cssSelector(".totalRow button")).click();
		WebElement country = driver.findElement(By.cssSelector("input[placeholder*='Country']"));
		Actions a = new Actions(driver);
		a.sendKeys(country, "Ind").build().perform();
		Thread.sleep(3000L);
		List<WebElement> countries = driver.findElements(By.cssSelector(".list-group-item"));
		for(WebElement count : countries) {
			if(count.getText().equalsIgnoreCase("India")) {
				count.click();
				break;
			}
		}
		driver.findElement(By.cssSelector(".action__submit")).click();
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText().trim();
		Assert.assertEquals("THANKYOU FOR THE ORDER.",confirmationMessage);
		driver.quit();
	}
}
