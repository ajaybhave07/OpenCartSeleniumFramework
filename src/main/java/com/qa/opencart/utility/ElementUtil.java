package com.qa.opencart.utility;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private Actions act;
	private JavascriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		this.act = new Actions(driver);
		this.jsUtil = new JavascriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		checkElementHighlight(ele);
		return ele;
	}

	private void checkElementHighlight(WebElement ele) {
		if (Boolean.parseBoolean(DriverFactory.highLight)) {
			jsUtil.flash(ele);
		}

	}

	public void enterText(By locator, String text) {
		getElement(locator).sendKeys(text);
	}

	public void enterText(By locator, CharSequence... value) {
		getElement(locator).sendKeys(value);
	}

	public void click(By locator) {
		getElement(locator).click();
	}

	public void threadSleep(int milliSeconds) {

		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementText(By locator) {
		return getElement(locator).getText();
	}

	public boolean isElementDisplayed(By locator) {

		try {
			return getElement(locator).isDisplayed();

		} catch (NoSuchElementException e) {
			System.out.println("Element " + locator + " is not displayed");
			return false;
		}
	}

	public void getElementAttribute(By locator, String attr) {
		getElement(locator).getDomProperty(attr);
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementCount(By locator) {
		return getElements(locator).size();
	}

	// to get list of element text with no black value.
	public List<String> getElementsTextList(By locator) {

		List<WebElement> eleList = getElements(locator);

		List<String> eleTextList = new ArrayList<String>();

		for (WebElement ele : eleList) {
			String text = ele.getText();

			if (text.length() != 0) {
				eleTextList.add(text);
			}

		}

		return eleTextList;
	}

	// to print the list of element text
	public void printElementList(By locator) {
		List<String> eleStringList = getElementsTextList(locator);

		for (String eleText : eleStringList) {

			System.out.println(eleText);
		}
	}

	public void doSearch(By searchField, String searchKey, By suggestionList, String suggestion) {

		enterText(suggestionList, searchKey);

		List<WebElement> eleLists = getElements(suggestionList);

		int totalSuggestion = eleLists.size();

		if (totalSuggestion == 0) {
			System.out.println("No Suggestion Found");
			throw new FrameworkException("No suggestion found ...!!!!");
		}

		for (WebElement ele : eleLists) {
			if (ele.getText().contains(suggestion)) {
				ele.click();
				break;
			}
		}
	}

	// this method is to check for a single web element
	public boolean isElementPresent(By locator) {
		if (getElementCount(locator) == 1) {
			return true;
		}
		return false;
	}

	// this method is to check if element present on UI is expected number of times.
	// use this method to check for more then 1 elements on UI
	public boolean isElementPresent(By locator, int expectedCount) {
		if (getElementCount(locator) == expectedCount) {
			return true;
		}
		return false;
	}

	public boolean isElementNotPresent(By locator) {
		if (getElementCount(locator) == 0) {
			return true;
		}
		return false;
	}

	public boolean isElementPresentMoreThenOnce(By locator) {
		if (getElementCount(locator) > 1) {
			return true;
		}
		return false;

	}

	public void sendKeysByClearingTextField(By searchElement, String value) {
		getElement(searchElement).clear();
		getElement(searchElement).sendKeys(value);
	}

	public void sendKeysByClearingTextField(WebElement searchElement, String value) {
		searchElement.clear();
		searchElement.sendKeys(value);
	}

	/******************************
	 * * Select Dropdown class utility
	 ***********************************/

	public Select getSelect(By locator) {
		return new Select(getElement(locator));
	}

	public void selectDropdownValueVisibleText(By locator, String text) {

		getSelect(locator).selectByVisibleText(text);
	}

	public void selectDropdownValueByIndex(By locator, int index) {

		getSelect(locator).selectByIndex(index);
	}

	public void selectDropdownValueByValue(By locator, String text) {

		getSelect(locator).selectByValue(text);
	}

	public List<WebElement> getDropdownOptionList(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions();

	}

	public void selectDropdownValue(By locator, String value) {

		List<WebElement> eleList = getElements(locator);

		for (WebElement ele : eleList) {
			if (ele.getText().contains(value)) {
				ele.click();
				break;
			}
		}
	}

	public List<String> getDropdownOptionListText(By locator) {

		List<WebElement> optionList = getSelect(locator).getOptions();
		List<String> textList = new ArrayList<>();
		for (WebElement ele : optionList) {
			textList.add(ele.getText());
		}

		return textList;
	}

	/*****************************************
	 * Action Class
	 *************************************************************/

	// do mouse over on parent menu and click child menu button
	public void parentChildMenu(By parentMenu, By childMenu) {

		getActions().moveToElement(getElement(parentMenu)).perform();

		threadSleep(2000);

		click(childMenu);
	}

	public Actions getActions() {
		return this.act;
	}

	public void multipleSubMenu(By locator1, By locator2, By locator3, By locator4) {

		click(locator1);
		threadSleep(2000);
		getActions().moveToElement(getElement(locator2)).perform();
		threadSleep(2000);
		getActions().moveToElement(getElement(locator3)).perform();
		threadSleep(2000);
		click(locator4);

	}

	public void actionsSendKeys(By locator, String value) {
		getActions().sendKeys(getElement(locator), value).perform();
	}

	public void actionsClick(By locator) {
		getActions().click(getElement(locator)).perform();
	}

	public void scrollToBottomUsingActions() {
		getActions().sendKeys(Keys.CONTROL).sendKeys(Keys.END).perform();
	}

	public void scrollToTopUsingActions() {
		getActions().sendKeys(Keys.CONTROL).sendKeys(Keys.HOME).perform();
	}

	/***************************** Wait Utils ****************/

	public void safeClick(By locator, int timeOut) {
		waitForElementVisibility(locator, timeOut).click();
	}

	public void safeSendKeys(By locator, int timeOut, String value) {
		waitForElementVisibility(locator, timeOut).sendKeys(value);
	}

	// method to get web-element after element loaded on DOM
	public WebElement waitForElementPresence(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// method to get web-element after element visible on page
	public WebElement waitForElementVisibility(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		checkElementHighlight(ele);
		return ele;
	}

	// method to get web-element after element loaded on DOM
	public WebElement waitForElementPresence(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	// method to get web-element after element visible on page with polling time
	public WebElement waitForElementVisibility(By locator, int timeOut, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// method to get web-element after element to be clickable
	public WebElement waitForElementClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// method to wait for elements to be visible
	public List<WebElement> waitForElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	/*********************************
	 * Wait for Title
	 ********************************/
	public String waitForFullTitle(String expectedTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.titleIs(expectedTitle));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println("Expected Title not found");
		}

		return "-1";
	}

	public String waitForPartialTitle(String expectedTitle, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.titleIs("HubSpot"));
			return driver.getTitle();
		} catch (TimeoutException e) {
			System.out.println("Expected Title not found");
		}
		return "-1";
	}

	/***************** Wait for URL *********************************/
	public String waitForURL(String URL, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.urlContains(URL));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			System.out.println("Expected URL not found");
		}

		return "-1";

	}

	public String waitForPartialURL(String URL, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.titleContains(URL));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			System.out.println("Expected URL not found");
		}

		return "-1";
	}

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public void enterTextAlert(String alertText, int timeOut) {
		waitForAlert(timeOut).sendKeys(alertText);

	}

	/***********************
	 * Frame Handeling with Wait
	 *****************************/

	public void waitForFrameAndSwitchToIt(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameAndSwitchToItIndex(int index, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrameAndSwitchToItString(String idorName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idorName));
	}

	public void waitForFrameAndSwitchToItElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	/******************* Window Handeling With Wait *******************************/
	public boolean waitForNewWindowToOpen(int windowCount, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			return wait.until(ExpectedConditions.numberOfWindowsToBe(windowCount));

		} catch (TimeoutException e) {
			System.out.println("number of windows are not matched....");
		}
		return false;

	}

	/************************* Fluent Wait **************************************/

	public WebElement waitForElementVisibilityFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).ignoring(ElementNotInteractableException.class)
				.withMessage("Element not visible");
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}
