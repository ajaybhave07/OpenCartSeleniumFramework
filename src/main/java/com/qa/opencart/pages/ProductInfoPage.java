package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utility.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil ele;

	private By productHeading = By.xpath("//h1");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]//li");
	private By productPricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]//li");

	private Map<String, String> productHashMap;
	private By imagesOnUI = By.xpath("//ul[@class='thumbnails']//img");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}

	public String getProductHeader() {
		return ele.waitForElementVisibility(productHeading, AppConstants.DEFAULT_SHORT_TIMEOUT).getText();
	}

	private void getProductMetaData() {
		List<WebElement> productInfo = ele.getElements(productMetaData);

		for (WebElement ele : productInfo) {
			String metaData = ele.getText();
			String[] metaArray = metaData.split(":");
			String metaKey = metaArray[0].trim();
			String metaValue = metaArray[1].trim();
			productHashMap.put(metaKey, metaValue);
		}
	}

	private void productPriceData() {
		List<WebElement> productPricing = ele.getElements(productPricingData);

		String productPrice = productPricing.get(0).getText().trim();
		String extTextPrice = productPricing.get(1).getText().split(":")[1].trim();
		productHashMap.put("productprice", productPrice);
		productHashMap.put("productExtPrice", extTextPrice);
	}

	public Map<String, String> getProductCompleteData() {
		productHashMap = new LinkedHashMap<String, String>();
		productHashMap.put("productHeader", getProductHeader());
		getProductMetaData();
		productPriceData();
		System.out.println(productHashMap);

		return productHashMap;

	}

	public int getImagesCount() {
		return ele.waitForElementsVisible(imagesOnUI,AppConstants.DEFAULT_MEDUM_TIMEOUT).size();
	}
}
