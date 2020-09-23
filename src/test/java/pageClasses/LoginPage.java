package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import base.BasePage;

public class LoginPage extends BasePage{
	WebDriver driver;
	public LoginPage(WebDriver driver1) {
		super(driver1);
		driver=driver1;
	}
	
	@FindBy(id="user_login")
	WebElement txtbxLogin;
	
	@FindBy(id="user_password")
	WebElement txtbxPassword;
	
	@FindBy(xpath="//input[@name='submit']")
	WebElement btnSubmit;
	
	@FindBy(xpath="//a[@class='brand']")
	WebElement logoBrand;
	
	
	public void verifyLogin(String uname, String pword) {
		txtbxLogin.sendKeys(uname);
		txtbxPassword.sendKeys(pword);
		btnSubmit.click();
	}

	public void goToHomePage() {
		//System.out.println("clicking the Brand logo");
		logoBrand.click();
	}

}
