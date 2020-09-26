package testcases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.relevantcodes.extentreports.LogStatus;
import base.BaseClass;
import pageClasses.AccountSummaryPage;
import pageClasses.HomePage;
import pageClasses.LoginPage;
import pageClasses.PayBillsPage;
import utilities.ExcelReader;
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class TC005VerifyPurchaseForeignCurrency extends BaseClass {
	HomePage hp;
	LoginPage lp;
	AccountSummaryPage asp;
	PayBillsPage pbp;

	@Test(dataProvider = "verifyPurchaseForeignCurrencyData")
	public void verifyPurchaseForeignCurrencyData(String pCurrency, String pAmount, String usd) {
		// System.out.println("TC005 started");
		test = report.startTest("Purchase Foreign Currency Test");
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
		test.log(LogStatus.INFO, "Navigating to Purchase Foreign currency tab.");
		pbp.purchaseForeignCash(pCurrency, pAmount, usd);
		test.log(LogStatus.INFO, "Request to purchase foreign currency is submitted.");
		String expectedText = "Foreign currency cash was successfully purchased.";
		String actualText = pbp.getAlertText();
		if (actualText.equals(expectedText)) {
			test.log(LogStatus.INFO, "Foreign currency purchased successfully.");
		} else {
			test.log(LogStatus.WARNING, "Foreign currency purchase failed.");
		}
		returnToHomePage();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualText, expectedText);
		sa.assertAll();
	}

	private void returnToHomePage() {
		asp = new AccountSummaryPage(driver);
		asp.logOut();
		lp.goToHomePage();

	}

	@DataProvider(name = "verifyPurchaseForeignCurrencyData")
	public Object[][] verifyTransferFundsDataProvider() throws IOException {
		ExcelReader reader = new ExcelReader(".\\src\\test\\resources\\excel\\TC005VerifyPurchaseForeignCurrency.xlsx",
				0);
		return (reader.getDataFromExcel());
	}
}
