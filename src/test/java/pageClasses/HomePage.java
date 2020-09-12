package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class HomePage extends BasePage{
	WebDriver driver;
	public HomePage(WebDriver driver1) {
		super(driver1);
	}
	
	@FindBy(id="signin_button")
	WebElement btnSignIn;
	
	public void goToLoginPage() {
		btnSignIn.click();
	}

}
