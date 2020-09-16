package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import base.BaseClass;
import pageClasses.HomePage;
import pageClasses.LoginPage;



public class TC001VerifyLogin extends BaseClass{
	HomePage hp;
	LoginPage lp;
	
  @Test
  public void verifyLogin() throws IOException {
	  test = report.startTest("VerifyLogin Test");
	  hp = new HomePage(driver);
	  hp.goToLoginPage();
	  test.log(LogStatus.INFO, "Clicked sign in button on homepage.");
	  lp = new LoginPage(driver);
	  lp.verifyLogin("username","password");
	  test.log(LogStatus.INFO, "Login credentials entered and submitted.");
	  String expectedTitle = "Zero - Account Summary"; //get this string from SRS
	  String actualTitle = driver.getTitle();
	  test.log(LogStatus.INFO, "Page title captured from landing page.");
	  SoftAssert sa = new SoftAssert();
	  sa.assertEquals(actualTitle, expectedTitle);
	  sa.assertAll();
	  //Assert.assertEquals(actualTitle, expectedTitle);	  
  }

  

}
