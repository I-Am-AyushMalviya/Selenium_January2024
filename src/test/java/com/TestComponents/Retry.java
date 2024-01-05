package com.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count = 0;
	int maxCount = 1;
	@Override
	public boolean retry(ITestResult result) {
		if(count < maxCount) {
			count ++;
			return true;
		}
		return false;
	}
	
}
