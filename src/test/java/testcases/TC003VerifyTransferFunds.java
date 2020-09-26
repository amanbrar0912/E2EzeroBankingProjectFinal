package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseClass;
import pageClasses.AccountSummaryPage;
import pageClasses.HomePage;
import pageClasses.LoginPage;
import pageClasses.PayBillsPage;
import pageClasses.TransferFundsPage;
import utilities.ExcelReader;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.DataProvider;

public class TC003VerifyTransferFunds extends BaseClass {
	HomePage hp;
	LoginPage lp;
	AccountSummaryPage asp;
	TransferFundsPage tfp;

	@Test(dataProvider = "verifyTransferFundsData")
	public void verifyTransferFunds(String fromAccount, String toAccount, String amount, String desc) {
		log = LogManager.getLogger(TC003VerifyTransferFunds.class.getName());
		test = report.startTest("Transfer Funds Test");
		hp = new HomePage(driver);
		hp.goToLoginPage();
		test.log(LogStatus.INFO, "Clicked sign in button on homepage.");
		log.debug("Clicked sign in button on homepage.");
		lp = new LoginPage(driver);
		lp.verifyLogin("username", "password");
		test.log(LogStatus.INFO, "Login credentials entered and submitted.");
		log.debug("Login credentials entered and submitted..");
		asp = new AccountSummaryPage(driver);
		asp.goToTransferFunds();
		test.log(LogStatus.INFO, "Navigating to Transfer funds page.");
		log.debug("Navigating to Transfer funds page.");
		tfp = new TransferFundsPage(driver);
		tfp.transferFunds(fromAccount, toAccount, amount, desc);
		test.log(LogStatus.INFO, "Funds Transferred.");
		log.debug("Funds Transferred.");
		String expectedText = "You successfully submitted your transaction.";
		String actualText = tfp.getAlertText();
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

	@DataProvider(name = "verifyTransferFundsData")
	public Object[][] verifyTransferFundsDataProvider() throws IOException {
		ExcelReader reader = new ExcelReader(".\\src\\test\\resources\\excel\\TC003VerifyTransferFundsData.xlsx", 0);
		return (reader.getDataFromExcel());
	}
}
