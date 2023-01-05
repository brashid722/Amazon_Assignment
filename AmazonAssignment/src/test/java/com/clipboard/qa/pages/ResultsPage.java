package com.clipboard.qa.pages;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.clipboard.qa.utils.Keywords;
import com.clipboard.qa.utils.TestBase;

public class ResultsPage extends TestBase
{

    static WebDriver ldriver;
	Keywords base = new Keywords(ldriver);
	
	public ResultsPage(WebDriver rdriver)
	{
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//span[normalize-space()='Brands']")
	WebElement brandFilters;
	
	@FindBy(xpath="//span[@class='a-size-base a-color-base'][normalize-space()='Samsung']")
	WebElement brandFilterName;
	
	@FindBy(xpath="//span[@class='a-button-text a-declarative']")
	WebElement sortDropdown;
	
	@FindBy(xpath="//span[text()='RESULTS']")
	WebElement resultsData;
	
	@FindBy(xpath="//div[@class='s-widget-container s-spacing-small s-widget-container-height-small "
			+ "celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_2']"
			+ "//span[@class='a-size-base-plus a-color-base a-text-normal']")
	WebElement product;
	
	@FindBy(xpath="//h1[@class='a-size-base-plus a-text-bold']")
	WebElement feature_item;
	
	@FindBy(xpath="//span[@id='productTitle']")
	WebElement selected_product_title;
	
	public void selectBrandValue()
	{
		Keywords.waitForElementPresence(brandFilters, "10");
		test.log(Status.INFO, "User tries to select: " + Keywords.getText(brandFilterName) + " checkbox");
		Keywords.click(brandFilterName);
		test.log(Status.INFO, "User checked Samsung checkbox in brands filters.");
	}
	
	public boolean validateResultsPagePresence()
	{
		boolean results_data = resultsData.isDisplayed();
		test.log(Status.INFO, "All records displayed successfully as per selected filter.");
		return results_data;
	}
	
	public void selectDropdownValue(String dropdownValueOption)	
	{
		WebElement selectedValue = null;
		Keywords.waitForElementToBeClickable(sortDropdown, "10");
		Keywords.click(sortDropdown);
		switch (dropdownValueOption)
		{
		case "Featured":
			selectedValue = driver.findElement(By.xpath("//a[text()='"+ dropdownValueOption + "']"));
			break;
		case "Price: High to Low":
			selectedValue = driver.findElement(By.xpath("//a[text()='"+ dropdownValueOption + "']"));
			break;
		case "Price: Low to High":
			selectedValue = driver.findElement(By.xpath("//a[text()='"+ dropdownValueOption + "']"));
			break;
		case "Avg. Customer Review":
			selectedValue = driver.findElement(By.xpath("//a[text()='"+ dropdownValueOption + "']"));
			break;
		case "Newest Arrivals":
			selectedValue = driver.findElement(By.xpath("//a[text()='"+ dropdownValueOption + "']"));
			break;
		}
		Keywords.waitForElementToLoad(selectedValue, "10");
		Keywords.click(selectedValue);
	}
	
	public void clickSecondHighestPrice()
	{
		String expected_product_name = product.getText();
		test.log(Status.INFO, "Expected product name is : " + expected_product_name);
		Keywords.click(product);
		String mainWindow = driver.getWindowHandle();
		
		//To handle new open window/windows
		Set<String> new_windows = driver.getWindowHandles();
		Iterator<String> i1 = new_windows.iterator();
		
		while(i1.hasNext())
		{
			String child_window = i1.next();
			
			if(!mainWindow.equalsIgnoreCase(child_window))
			{
				//Switch to child window
				driver.switchTo().window(child_window);
				String actual_product_title = selected_product_title.getText();
				test.log(Status.INFO, "actual product name is :" + actual_product_title);
				Assert.assertEquals(actual_product_title, expected_product_name, "Actual and Expected "
						+ "product title are equal.");
				
				boolean about_this_item_status = feature_item.isDisplayed();
				Assert.assertTrue(about_this_item_status, "About this Item section is present.");
				
				//closing the child window
				driver.close();
			}
		}
		driver.switchTo().window(mainWindow);
	}
	
}
