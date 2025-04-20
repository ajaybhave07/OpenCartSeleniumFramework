package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil ele;
	
	private By searchHeader = By.cssSelector("div#content h2");
	private By resultProducts = By.xpath("//div[@class='product-thumb']");
	
	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}
	
	public String getSearchHeader() {
		return ele.waitForElementVisibility(searchHeader,AppConstants.DEFAULT_MEDUM_TIMEOUT).getText();
	}
	
	
	public int getSearchResultCount() {
		return ele.waitForElementsVisible(resultProducts, AppConstants.DEFAULT_MEDUM_TIMEOUT).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		ele.click(By.xpath("//a[text()='"+productName+"']"));
		return new ProductInfoPage(driver);
	}

}
