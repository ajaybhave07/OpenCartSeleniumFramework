package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void navigateToProductPage() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test
	public void productHeaderTest() {
		resultPage = accPage.doSearch(AppConstants.MACBOOK_PRODUCT);
		productInfoPage = resultPage.selectProduct(AppConstants.MACBOOK_PRO);
		String productName = productInfoPage.getProductHeader();
		Assert.assertEquals(productName, AppConstants.MACBOOK_PRO);
	}

	@Test
	public void productInfoTest() {
		resultPage = accPage.doSearch(AppConstants.MACBOOK_PRODUCT);
		productInfoPage = resultPage.selectProduct(AppConstants.MACBOOK_PRO);
		Map<String, String> actualmap = productInfoPage.getProductCompleteData();

		softAssert.assertEquals(actualmap.get("Brand"), "Apple");
		softAssert.assertEquals(actualmap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actualmap.get("Reward Points"), "800");
		softAssert.assertEquals(actualmap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actualmap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(actualmap.get("productExtPrice"), "$2,000.00");

		softAssert.assertAll();
	}

	@DataProvider
	public Object[][] getProductImagesCountData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "imac", "iMac", 3 },
				{ "samsung", "Samsung SyncMaster 941BW", 1 }, { "canon", "Canon EOS 5D", 3 } };
	}

	@Test(dataProvider = "getProductImagesCountData")
	public void productImgesCountTest(String searchName, String selectName, int expectedImages) {
		resultPage = accPage.doSearch(searchName);
		productInfoPage = resultPage.selectProduct(selectName);
		softAssert.assertEquals(productInfoPage.getImagesCount(), expectedImages);
		softAssert.assertAll();

	}
}
