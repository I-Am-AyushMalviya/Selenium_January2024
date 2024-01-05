package com.StandAloneExtentReport;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportStandAlone {
	
	ExtentReports extent;
	@BeforeTest
	public void config() {
		String path = System.getProperty("user.dir")+"\\reporter\\index.html" ;
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester","Ayush Malviya");
	}

	@Test
	public void initialDemo() {
		extent.createTest("Initial Demo");
		WebDriver driver = new FirefoxDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		System.out.println(driver.getTitle());
		extent.flush();
	}
}
