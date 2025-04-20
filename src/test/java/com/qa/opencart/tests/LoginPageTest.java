	package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginPageTest extends BaseTest {

	@Epic("Epic : 101 design open cart page")
	@Story("user story login_design")
	@Feature("login feature")
	@Severity(SeverityLevel.MINOR)
	@Description("login page test")
	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE, "title is incorrect");
	}
	
	@Epic("Epic : 101 design open cart page")
	@Story("user story login_design")
	@Feature("login feature")
	@Severity(SeverityLevel.MINOR)
	@Description("login page test")
	@Test
	public void loginPageURLTest() {
		String actualURL = loginPage.getLoginpageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.LOGIN_PAGE_URL));
	}
	
	@Epic("Epic : 101 design open cart page")
	@Story("user story login_design")
	@Feature("login feature")
	@Severity(SeverityLevel.MINOR)
	@Description("login page test")
	@Test
	public void forgotPasswordLinkTest() {
		boolean linkVisible = loginPage.isForgotPasswordLinkExist();
		Assert.assertTrue(linkVisible);
	}
	
	@Epic("Epic : 101 design open cart page")
	@Story("user story login_design")
	@Feature("login feature")
	@Severity(SeverityLevel.NORMAL)
	@Description("login page test")
	@Test
	public void isLogoExist() {
		Assert.assertTrue(loginPage.isLogoDisplayed());

	}

	@Epic("Epic : 101 design open cart page")
	@Story("user story login_design")
	@Feature("login feature")
	@Severity(SeverityLevel.CRITICAL)
	@Description("login page test")
	@Test(priority = Integer.MAX_VALUE)
	public void loginTest() {
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		AccountsPage acc = loginPage.doLogin(username, password);
		Assert.assertEquals(acc.getAccountPageTitle(), AppConstants.ACCOUNT_PAGE_TITLE);
	}
}
