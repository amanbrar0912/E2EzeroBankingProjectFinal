package testcases;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import base.BaseClass;
import pageClasses.AccountSummaryPage;
import pageClasses.HomePage;
import pageClasses.LoginPage;
import pageClasses.OnlineStatementPage;
import utilities.ExcelReader;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class TC006VerifyOnlineStatementDownload extends BaseClass {
	HomePage hp;
	LoginPage lp;
	AccountSummaryPage asp;
	OnlineStatementPage osp;
	private String fileName = "8534567";
	private String downloadPath = "C:\\Users\\HP\\Downloads";

	@Test(dataProvider = "verifyOnlineStatementDownloadData")
	public void verifyOnlineStatementDownload(String account) throws InterruptedException {
		// deleting any old bank statement present at target directory.
		removeOldFile(downloadPath, fileName);
		test = report.startTest("Online Statement Download Test");
		hp = new HomePage(driver);
		hp.goToLoginPage();
		test.log(LogStatus.INFO, "Clicked sign in button on homepage.");
		lp = new LoginPage(driver);
		lp.verifyLogin("username", "password");
		test.log(LogStatus.INFO, "Login credentials entered and submitted.");
		asp = new AccountSummaryPage(driver);
		asp.goToOnlineStatements();
		System.out.println(account);
		osp = new OnlineStatementPage(driver);
		osp.downloadOnlineStatement(account);
		test.log(LogStatus.INFO, "Online Statement Downloaded.");
		Thread.sleep(5000);
		Assert.assertTrue(isFileDownloaded(downloadPath, fileName));
		osp.logOut();
	}

	public boolean isFileDownloaded(String downloadPath, String fileName) {
		boolean fileIsPresent = false;
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().contains(fileName)) {
				// System.out.println(dir_contents[i].getName()+"found the file");
				fileIsPresent = true;
				test.log(LogStatus.INFO, "1 new Online statement confirmed at downloadPath.");
				break;
			} else {
				test.log(LogStatus.WARNING, "No new Online statement found at downloadPath.");
			}
		}
		return fileIsPresent;
	}

	public void removeOldFile(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dir_contents = dir.listFiles();

		for (int i = 0; i < dir_contents.length; i++) {
			if (dir_contents[i].getName().contains(fileName)) {
				dir_contents[i].delete();
				// System.out.println("1 file deleted");
				test.log(LogStatus.INFO, "1 old Online statement deleted from downloadPath.");
			}
		}
	}

	@DataProvider(name = "verifyOnlineStatementDownloadData")
	public Object[][] dp() throws IOException {
		ExcelReader reader = new ExcelReader(".\\src\\test\\resources\\excel\\TC006VerifyOnlineStatementDownload.xlsx",
				0);
		return (reader.getDataFromExcel());
	}
}
