package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseClass;
import pageClasses.HomePage;
import pageClasses.LoginPage;


public class TC001VerifyLogin extends BaseClass{
	HomePage hp;
	LoginPage lp;
 
  @Test
  public void verifyLogin() throws IOException {
	 // driver=initializeDriver();
	 // driver.get("http://zero.webappsecurity.com/");
	  hp = new HomePage(driver);
	  hp.goToLoginPage();
	  lp = new LoginPage(driver);
	  lp.verifyLogin("username","password");
	  String expectedTitle = "Zero - Account Summary"; //get this string from SRS
	  String actualTitle = driver.getTitle();
	  SoftAssert sa = new SoftAssert();
	  sa.assertEquals(actualTitle, expectedTitle);
	  sa.assertAll();
	  //Assert.assertEquals(actualTitle, expectedTitle);	  
  }

  

}
