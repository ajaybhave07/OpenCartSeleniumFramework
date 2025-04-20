package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ResultsPage resultPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage regPage;
	
	
	protected SoftAssert softAssert = new SoftAssert();
	
	@Parameters({"browser"})
	@BeforeTest
	public void setUp(@Optional("chrome") String browser) {
		df = new DriverFactory();
		prop = df.initProperties(); //here we will have prop with browser value coming from properties file

		if(browser!=null) { //if browser pass !null means we want to modify the browser other then properties file
			prop.setProperty("browser", browser); //updating browser value to properties file only if its not null
		}
		
		//driver = df.initDriver(prop);
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
