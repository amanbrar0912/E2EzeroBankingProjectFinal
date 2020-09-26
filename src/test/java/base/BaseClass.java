package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import pageClasses.AccountSummaryPage;

public class BaseClass {
	public static WebDriver driver;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	public Logger log = LogManager.getLogger(BaseClass.class.getName());
	Properties prop;
	AccountSummaryPage asp;

	@BeforeTest
	public WebDriver initializeDriver() throws IOException {
		// log = LogManager.getLogger(this.getClass().getName());

		// log.info("I am creating the message."); log.debug("this is debug message");
		// log.error("I am error message."); log.fatal("I am the fatal message");

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
		// report.startTest(test);
		return driver;
	}

	@AfterTest
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		// report.endTest(test);
		// report.flush();
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
