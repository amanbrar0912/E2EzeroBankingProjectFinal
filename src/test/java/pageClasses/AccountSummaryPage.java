package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class AccountSummaryPage extends BasePage {
	public AccountSummaryPage(WebDriver driver1) {
		super(driver1);
	}
	
	@FindBy(xpath="//body/div[@class='wrapper']/div[@class='navbar navbar-fixed-top']/div[@class='navbar-inner']/div[@class='container']/div[@id='settingsBox']/ul[@class='nav float-right']/li[3]/a[1]")
	WebElement userLogo;
	
	@FindBy(xpath="//a[@id='logout_link']")
	WebElement logOutLink;
	
	@FindBy(xpath="//a[contains(text(),'Pay Bills')]")
	WebElement tabPayBills;
	
	public void goToPayBills() {
		tabPayBills.click();
	}
	
	public void logOut() {
		userLogo.click();
		logOutLink.click();
		System.out.println("logout done");
	}
}
