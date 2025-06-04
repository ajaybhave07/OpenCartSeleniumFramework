package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import com.qa.opencart.error.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	public static String highLight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		String URL = prop.getProperty("URL");
		System.out.println("Browser Name is " + browserName);

		highLight = System.getProperty("highlight");

		OptionsManager opManger = new OptionsManager(prop);

		switch (browserName.trim()) {
		case "chrome":
			// driver = new ChromeDriver(opManger.getChromeOptions());
			tlDriver.set(new ChromeDriver(opManger.getChromeOptions()));
			break;
		case "firefox":
			// driver = new FirefoxDriver(opManger.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(opManger.getFireFoxOptions()));
			break;
		case "edge":
			// driver = new EdgeDriver(opManger.getEdgeOptions());
			tlDriver.set(new EdgeDriver(opManger.getEdgeOptions()));
			break;

		default:
			System.out.println(AppError.INVALID_BROWSER_MSG);
			throw new BrowserException(AppError.INVALID_BROWSER + browserName);
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(URL);

		// return driver;
		return getDriver();

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProperties() {

		try {
			FileInputStream fis = null;
			prop = new Properties();

			String environmentName = System.getProperty("env");
			System.out.println("environment name " + environmentName);

			if (environmentName == null)  { // if no environment is passed from maven , we use QA environment
				System.out.println("Env is null hence we run tests on QA environment");
				fis = new FileInputStream(".\\src\\test\\recources\\config\\config_qa_env.properties");
			} else {

				switch (environmentName.trim()) {
				case "qa":
					fis = new FileInputStream(".\\src\\test\\recources\\config\\config_qa_env.properties");
					break;
				case "dev":
					fis = new FileInputStream(".\\src\\test\\recources\\config\\config_qa_env.properties");
					break;

				case "uat":
					fis = new FileInputStream(".\\src\\test\\recources\\config\\config_qa_env.properties");
					break;
				default:
					System.out.println("Environment Provided is invalid...." + environmentName);
					throw new FrameworkException("Invalid ENV Name");
				}

			} 
			prop.load(fis);

		} catch (FileNotFoundException fne) {
			fne.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return prop;
	}

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
