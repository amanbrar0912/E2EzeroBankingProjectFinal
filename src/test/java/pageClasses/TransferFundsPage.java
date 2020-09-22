package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;

public class TransferFundsPage extends BasePage {

	public TransferFundsPage(WebDriver driver1) {
		super(driver1);
	}
	@FindBy(xpath="//body/div[@class='wrapper']/div[@class='navbar navbar-fixed-top']/div[@class='navbar-inner']/div[@class='container']/div[@id='settingsBox']/ul[@class='nav float-right']/li[3]/a[1]")
	WebElement userLogo;
	
	@FindBy(xpath="//a[@id='logout_link']")
	WebElement logOutLink;

	@FindBy(xpath = "//select[@id='tf_fromAccountId']")
	WebElement selectFromAccount;

	@FindBy(xpath = "//select[@id='tf_toAccountId']")
	WebElement selectToAccount;

	@FindBy(xpath = "//input[@id='tf_amount']")
	WebElement txtbxAmount;

	@FindBy(xpath = "//input[@id='tf_description']")
	WebElement txtbxDescription;

	@FindBy(xpath = "//button[@id='btn_submit']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//button[@id='btn_submit']")
	WebElement btnSubmit;

	@FindBy(xpath = "//div[@class='alert alert-success']")
	WebElement txtAlert;
	
	public void transferFunds(String frmAccount,String toAccount, String amount, String desc) {
		Select select1 = new Select(selectFromAccount);
		select1.selectByValue(frmAccount);
		Select select2 = new Select(selectToAccount);
		select2.selectByValue(toAccount);
		txtbxAmount.sendKeys(amount);
		txtbxDescription.sendKeys(desc);
		btnContinue.click();
		btnSubmit.click();
	}
	public String getAlertText() {
		String actualText=txtAlert.getText();
		return actualText;
	}
	public void logOut() {
		userLogo.click();
		logOutLink.click();
		//System.out.println("logout done");
	}
}
