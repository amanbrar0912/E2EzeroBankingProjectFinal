package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import base.BaseClass;
import pageClasses.AccountSummaryPage;
import pageClasses.HomePage;
import pageClasses.LoginPage;
import pageClasses.PayBillsPage;
import utilities.ExcelReader;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class TC004VerifyPaySavedPayee extends BaseClass {
	HomePage hp;
	LoginPage lp;
	AccountSummaryPage asp;
	PayBillsPage pbp;

	@Test(dataProvider = "verifyPaySavedPayeeData")
	public void verifyPaySavedPayee(String payee, String account, String amount, String date, String desc) {
		test = report.startTest("Pay Saved Payee Test");
		hp = new HomePage(driver);
		hp.goToLoginPage();
		test.log(LogStatus.INFO, "Clicked sign in button on homepage.");
		lp = new LoginPage(driver);
		lp.verifyLogin("username", "password");
		test.log(LogStatus.INFO, "Login credentials entered and submitted.");
		asp = new AccountSummaryPage(driver);
		asp.goToPayBills();
		test.log(LogStatus.INFO, "Navigating to Pay Bills tab.");
		pbp = new PayBillsPage(driver);
		pbp.paySavedPayee(payee, account, amount, date, desc);
		test.log(LogStatus.INFO, "Bill paid.");
		String expectedText = "The payment was successfully submitted.";
		String actualText = pbp.getAlertText();
		// if (actualText.equals(expectedText)) {
		returnToHomePage();
		// }
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualText, expectedText);
		sa.assertAll();
	}

	private void returnToHomePage() {
		asp = new AccountSummaryPage(driver);
		asp.logOut();
		lp.goToHomePage();
	}

	@DataProvider(name = "verifyPaySavedPayeeData")
	public Object[][] verifyTransferFundsDataProvider() throws IOException {
		ExcelReader reader = new ExcelReader(".\\src\\test\\resources\\excel\\TC004VerifyPaySavedPayee.xlsx", 0);
		return (reader.getDataFromExcel());
	}
}
