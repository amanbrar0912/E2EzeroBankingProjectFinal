package pageClasses;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;

public class OnlineStatementPage extends BasePage {
	public OnlineStatementPage(WebDriver driver1) {
		super(driver1);
	}
	@FindBy(xpath="//body/div[@class='wrapper']/div[@class='navbar navbar-fixed-top']/div[@class='navbar-inner']/div[@class='container']/div[@id='settingsBox']/ul[@class='nav float-right']/li[3]/a[1]")
	WebElement userLogo;
	
	@FindBy(xpath="//a[@id='logout_link']")
	WebElement logOutLink;
	
	@FindBy(xpath="//select[@id='os_accountId']")
	WebElement selectAccount;
	@FindBy(xpath="//a[contains(text(),'2012')]")
	WebElement tab2012;
	@FindBy(xpath="//a[contains(text(),'2011')]")
	WebElement tab2011;
	@FindBy(xpath="//a[contains(text(),'2010')]")
	WebElement tab2010;
	@FindBy(xpath="//a[contains(text(),'2009')]")
	WebElement tab2009;
	@FindBy(partialLinkText = "Statement 05/12/11")
	WebElement linkStmt01;
	
	
	public void downloadOnlineStatement(String account) {
		System.out.println(account);
		Select s1 = new Select(selectAccount);
		System.out.println("Select obj created.");
		selectAccount.sendKeys(Keys.TAB);
		tab2011.click();
		System.out.println("account selected");
		linkStmt01.click();
		System.out.println("pdf link clicked");
	}
	
	public void logOut() {
		userLogo.click();
		logOutLink.click();
		//System.out.println("logout done");
	}
}
