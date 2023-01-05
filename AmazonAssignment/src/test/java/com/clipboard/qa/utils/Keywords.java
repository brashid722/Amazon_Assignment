package com.clipboard.qa.utils;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Keywords {
	
	static WebDriver driver;
	
	public Keywords(WebDriver driver) {
		
		Keywords.driver = driver;
	}
	
	public static boolean waitForElementPresence(WebElement element, String timeout)
	{
		try {
			Keywords.waitForElementToLoad(element, timeout);
			return element.isDisplayed();
		}
		catch(Exception E) {
			Reporter.log("Error Waiting for Element:" + element);
			return false;
		}
		
	}
	
	public static void waitForElementToBeClickable(WebElement sortDropdown, String timeout)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.elementToBeClickable(sortDropdown));
		}
		catch (Exception E) {
			Reporter.log("Error Waiting for Element:" + sortDropdown);
		}
		
	}
	
	public static void waitForElementToLoad(WebElement element, String timeout)
	{
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch(Exception E) {
			Reporter.log("Error Waiting for Element:" + element);
		}
	}
	
	public static void click(WebElement element)
	{
		Keywords.waitForElementToBeClickable(element, "10");
		element.click();
	}
	
	public static String getText(WebElement element)
	{
		Keywords.waitForElementToLoad(element, "10");
		return element.getText();
	}	

}
