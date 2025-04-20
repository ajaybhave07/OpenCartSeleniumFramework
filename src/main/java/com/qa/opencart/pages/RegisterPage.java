package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

	private By successMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean userRegisteration(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {

		eleUtil.waitForElementVisibility(this.firstName, AppConstants.DEFAULT_MEDUM_TIMEOUT).sendKeys(firstName);

		eleUtil.sendKeysByClearingTextField(this.lastName, lastName);
		eleUtil.sendKeysByClearingTextField(this.email, email);
		eleUtil.sendKeysByClearingTextField(this.telephone, telephone);
		eleUtil.sendKeysByClearingTextField(this.password, password);

		eleUtil.sendKeysByClearingTextField(this.confirmpassword, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.click(agreeCheckBox);
		} else {
			eleUtil.click(subscribeNo);
		}

		eleUtil.click(agreeCheckBox);
		
		eleUtil.click(continueButton);

		String successMesg = eleUtil.waitForElementVisibility(successMessg, AppConstants.DEFAULT_MEDUM_TIMEOUT)
				.getText();
		System.out.println(successMesg);

		if (successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
			eleUtil.click(logoutLink);
			eleUtil.click(registerLink);
			return true;
		} else {
			return false;
		}

	}
}
