package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil ele;

	// 1. private By locators: also known as Page Objects
	private By username = By.xpath("//input[@id='input-email']");
	private By password = By.xpath("//input[@id='input-password']");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPass = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By image = By.xpath("//img[@title='naveenopencart']");
	private By registerLink = By.xpath("//aside//a[text()='Register']");
	
	// 2. public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.ele = new ElementUtil(driver);
	}

	// 3. page actions/methods
	@Step("get the login page title as String")
	public String getLoginPageTitle() {
		String title =
		ele.waitForFullTitle(AppConstants.LOGIN_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println(title);
		return title;
	}

	@Step("get the login page URL as String")
	public String getLoginpageURL() {
		String URL = ele.waitForURL(AppConstants.LOGIN_PAGE_URL, AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println(URL);
		return URL;
	}
	
	public boolean isForgotPasswordLinkExist() {
		return ele.isElementDisplayed(forgotPass);
	}
	
	public AccountsPage doLogin(String userName , String pass) {
		ele.waitForElementVisibility(username, AppConstants.DEFAULT_MEDUM_TIMEOUT).sendKeys(userName);
		ele.getElement(password).sendKeys(pass);
		ele.waitForElementClickable(loginBtn,AppConstants.DEFAULT_SHORT_TIMEOUT).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		//next landing page object
		 return new AccountsPage(driver);
	}
	
	public boolean isLogoDisplayed() {
		return  ele.isElementDisplayed(image);
	}
	
	public RegisterPage navigateToRegistrationPage() {
		ele.waitForElementVisibility(registerLink, AppConstants.DEFAULT_SHORT_TIMEOUT).click();
		return new RegisterPage(driver);
	}
	
}
