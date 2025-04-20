package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ExcelUtil;

public class RegisterPageTest extends BaseTest{

	//login page is our landing page of the application , to navigate to any page we have
	//to start with LoginPage
	@BeforeClass
	public void regSetup() {
		regPage = loginPage.navigateToRegistrationPage();
	}
	
	public String getRandomEmail(){
		return "uiautomation"+System.currentTimeMillis()+"@open.com";
	}
	
	
	@DataProvider
	public Object[][] getRegistationData() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_EXCEL_SHEET_NAME);
		
	}
	@Test(dataProvider = "getRegistationData",enabled = false)
	public void userRegistrationTest(String fistName , String lastName , String telephone , String password , String subscribe) {
		Assert.assertTrue(regPage.userRegisteration(fistName, lastName, getRandomEmail(), telephone, password, subscribe));
	}
}
