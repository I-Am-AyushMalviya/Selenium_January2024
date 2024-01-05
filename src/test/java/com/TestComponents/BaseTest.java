package com.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.apache.commons.io.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.logging.log4j.core.util.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.PageObject.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "//src//main//java//com//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String webBrowser = prop.getProperty("browser");
		
		if(webBrowser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			
		}
		else if(webBrowser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			
		}
		else if(webBrowser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public List<HashMap<String, String>> getDataFromJSON(String path) throws IOException {
		
		String jsonContent = FileUtils.readFileToString(new File(path),
				 StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data =  mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File Destination = new File(System.getProperty("user.dir")+ "//reports//" + testCaseName+ ".png");
		FileUtils.copyFile(source,Destination);
		return System.getProperty("user.dir")+ "//reports//" + testCaseName+ ".png";	
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchingApplication() throws IOException {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}
}
