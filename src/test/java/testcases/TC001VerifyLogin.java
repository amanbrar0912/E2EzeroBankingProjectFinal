package testcases;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import base.BaseClass;
import pageClasses.AccountSummaryPage;
import pageClasses.HomePage;
import pageClasses.LoginPage;
import utilities.ExcelReader;

public class TC001VerifyLogin extends BaseClass {
	HomePage hp;
	LoginPage lp;
	AccountSummaryPage asp;

	@Test(dataProvider = "loginData")
	public void verifyLogin(String login, String password) throws IOException {
		log = LogManager.getLogger(TC001VerifyLogin.class.getName());
		test = report.startTest("Login Test");
		hp = new HomePage(driver);
		hp.goToLoginPage();
		test.log(LogStatus.INFO, "Clicked sign in button on homepage.");
		log.debug("Clicked sign in button on homepage.");
		lp = new LoginPage(driver);
		lp.verifyLogin(login, password);
		test.log(LogStatus.INFO, "Login credentials entered and submitted.");
		log.debug("Login credentials entered and submitted.");
		String expectedTitle = "Zero - Account Summary"; // get this string from SRS
		String actualTitle = driver.getTitle();
		test.log(LogStatus.INFO, "Page title captured from landing page.");
		log.debug("Page title captured from landing page.");
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualTitle, expectedTitle);
		sa.assertAll();

		if (actualTitle.equals(expectedTitle)) {
			logOut();
		}
		returnToHomePage();
	}

	@DataProvider(name = "loginData")
	public Object[][] passData() throws IOException {
		ExcelReader reader = new ExcelReader(".\\src\\test\\resources\\excel\\TC001VerifyLoginData.xlsx", 0);
		return (reader.getDataFromExcel());
	}

	public void logOut() {
		// System.out.println("log out");
		asp = new AccountSummaryPage(driver);
		asp.logOut();
	}

	public void returnToHomePage() {
		lp.goToHomePage();
	}

}
