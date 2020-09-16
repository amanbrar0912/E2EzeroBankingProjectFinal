package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass {
	public WebDriver driver;
	// public ExtentReports report= new
	// ExtentReports(".\\src\\test\\resources\\reports\\report1.html", false);
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeTest
	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
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
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(prop.getProperty("testsiteURL"));
		return driver;
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		test.log(LogStatus.INFO, "WebDriver closed.");
		report.endTest(test);
		report.flush();
	}
}
