package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.AnnotationTransformer;
import com.qa.opencart.listeners.ExtentReportListener;

@Listeners({ExtentReportListener.class , AnnotationTransformer.class})
public class AccountPageTest extends BaseTest {

	// this method will execute before any test of AccountspageTest executes and it
	// will
	// do login for us ,so that remaining @Test can only focus on AccountsPage
	// methods.
	@BeforeClass
	public void accountPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accountsPageTitleTest() {
		String title = accPage.getAccountPageTitle();
		Assert.assertEquals(title, AppConstants.ACCOUNT_PAGE_TITLE, "Title did not match");
	}

	@Test
	public void isLogoutLinkExistTest() {
		boolean flag = accPage.isLogoutLinkExist();
		Assert.assertTrue(flag);
	}

	@Test
	public void accPageheadersCountTest() {
		int count = accPage.getTotalAccountsPageheader();
		Assert.assertEquals(count, AppConstants.ACC_PAGE_HEADER_COUNT);
	}

	@Test
	public void accPageHeaderValueTest() {
		List<String> accPageList = accPage.getAccPageHeaders();
		Assert.assertEquals(accPageList, AppConstants.HEADERS_LIST);
	}

	@DataProvider
	public Object[][] getSearchKey() {
		return new Object[][] { { "macbook", 3 }, { "imac", 1 }, { "samsung", 2 } };
	}

	@Test(dataProvider = "getSearchKey")
	public void searchCountTest(String searchKey ,int expectedCount) {
		resultPage = accPage.doSearch(searchKey);
		int searchResults = resultPage.getSearchResultCount();
		Assert.assertEquals(expectedCount, searchResults);
	}
	
	@DataProvider
	public Object[][] getProductName() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "imac", "iMac" }, { "samsung", "Samsung SyncMaster 941BW" } };
	}
	@Test(dataProvider = "getProductName")
	public void searchTest(String productName , String expectedHeader) {
		resultPage = accPage.doSearch(productName);
		productInfoPage = resultPage.selectProduct(expectedHeader);
		String headerValue = productInfoPage.getProductHeader();
		Assert.assertEquals(headerValue, expectedHeader);
	}

}
