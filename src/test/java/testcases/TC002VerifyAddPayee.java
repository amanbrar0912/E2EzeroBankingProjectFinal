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

public class TC002VerifyAddPayee extends BaseClass {
	HomePage hp;
	LoginPage lp;
	AccountSummaryPage asp;
	PayBillsPage pbp;

	@Test(dataProvider = "AddPayeeData")
	public void verifyAddPayee(String pName, String pAddress, String pAccount, String pDetails) {
		//test = report.startTest("Verify Add Payee Test");
		hp = new HomePage(driver);
		hp.goToLoginPage();
		test.log(LogStatus.INFO, "Clicked sign in button on homepage.");
		lp = new LoginPage(driver);
		lp.verifyLogin("username", "password");
		test.log(LogStatus.INFO, "Login credentials entered and submitted.");
		asp = new AccountSummaryPage(driver);
		asp.goToPayBills();
		test.log(LogStatus.INFO, "Navigated to PayBills page.");
		pbp = new PayBillsPage(driver);
		pbp.goToAddNewPayee();
		test.log(LogStatus.INFO, "Navigated to addNewPayee tab.");
		pbp.addNewPayee(pName, pAddress, pAccount, pDetails);
		test.log(LogStatus.INFO, "New payee data added.");
		String expectedText = "The new payee " + pName + " was successfully created.";
		String actualText = pbp.getAlertText();
		// if (actualText.equals(expectedText)) {
		returnToHomePage();
		// }
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualText, expectedText);
		sa.assertAll();
	}

	@DataProvider(name = "AddPayeeData")
	public Object[][] AddPayeeDataProvider() throws IOException {
		ExcelReader reader = new ExcelReader(".\\src\\test\\resources\\excel\\TC002VerifyAddPayeeData.xlsx", 0);
		return (reader.getDataFromExcel());
	}

	public void returnToHomePage() {
		//System.out.println("returning to homepage");
		asp = new AccountSummaryPage(driver);
		asp.logOut();
		lp.goToHomePage();
	}
}
