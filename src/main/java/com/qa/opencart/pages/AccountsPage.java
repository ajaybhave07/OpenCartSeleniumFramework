package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil ele;

	private By logout = By.xpath("//aside//a[text()='Logout']");
	private By allHeaders = By.xpath("//div[@id='content']//h2");
	private By searchBox = By.xpath("//input[@placeholder='Search']");
	private By searchBtn = By.xpath("//div[@id='search']//button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}

	public String getAccountPageTitle() {
		String title = ele.waitForFullTitle(AppConstants.ACCOUNT_PAGE_TITLE, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println(title);
		return title;
	}

	public boolean isLogoutLinkExist() {
		ele.scrollToBottomUsingActions();
		return ele.waitForElementVisibility(logout, AppConstants.DEFAULT_MEDUM_TIMEOUT).isDisplayed();
		
	}

	public List<String> getAccPageHeaders() {
		List<WebElement> eleList = ele.waitForElementsVisible(allHeaders, AppConstants.DEFAULT_SHORT_TIMEOUT);
		List<String> headersList = new ArrayList<String>();
		for (WebElement element : eleList) {
			headersList.add(element.getText());
		}

		return headersList;
	}

	public int getTotalAccountsPageheader() {
		return ele.waitForElementsVisible(allHeaders, AppConstants.DEFAULT_SHORT_TIMEOUT).size();

	}
	
	public ResultsPage doSearch(String searchItem) {
		
		WebElement searchElement = ele.waitForElementVisibility(searchBox, AppConstants.DEFAULT_SHORT_TIMEOUT);
		ele.sendKeysByClearingTextField(searchElement, searchItem);
		ele.click(searchBtn);
		return new ResultsPage(driver);
	}

}
