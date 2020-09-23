package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import pageClasses.AccountSummaryPage;

public class BaseClass {
	public static WebDriver driver;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	Properties prop;
	AccountSummaryPage asp;

	@BeforeTest
	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
		prop.load(fis);
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			// firefox code
		} else if (browser.equalsIgnoreCase("IE")) {
			// IE Code
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("testsiteURL"));
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		return driver;
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		report.endTest(test);
		report.flush();

	}

	/*
	 * public void getScreenShotPath(String testCaseName, WebDriver driver) throws
	 * IOException { String dateName = new
	 * SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); TakesScreenshot ts =
	 * (TakesScreenshot)driver; File source = ts.getScreenshotAs(OutputType.FILE);
	 * String destinationFile = System.getProperty("user.dir")+
	 * "\\src\\test\\resources\\reports\\"+testCaseName+dateName+".png";
	 * FileUtils.copyFile(source, new File(destinationFile)); }
	 */
}
