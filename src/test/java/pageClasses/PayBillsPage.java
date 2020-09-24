package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;

public class PayBillsPage extends BasePage {

	public PayBillsPage(WebDriver driver1) {
		super(driver1);
	}

	@FindBy(xpath = "//a[contains(text(),'Add New Payee')]")
	WebElement tabAddNewPayee;

	@FindBy(xpath = "//input[@id='np_new_payee_name']")
	WebElement txtbxPayeeName;

	@FindBy(xpath = "//textarea[@id='np_new_payee_address']")
	WebElement txtbxPayeeAddress;

	@FindBy(xpath = "//input[@id='np_new_payee_account']")
	WebElement txtbxAccount;

	@FindBy(xpath = "//input[@id='np_new_payee_details']")
	WebElement txtbxPayeeDetails;
	
	@FindBy(xpath = "//input[@id='add_new_payee']")
	WebElement btnAdd;
	
	@FindBy(xpath = "//select[@id='sp_payee']")
	WebElement selectPayee;
	
	
	@FindBy(xpath = "//select[@id='sp_account']")
	WebElement selectAccount;
	
	
	@FindBy(xpath = "//input[@id='sp_amount']")
	WebElement txtbxAmount;
	
	
	@FindBy(xpath = "//input[@id='sp_date']")
	WebElement txtbxDate;
	
	
	@FindBy(xpath = "//input[@id='sp_description']")
	WebElement txtbxDesc;
	
	@FindBy(xpath = "//input[@id='pay_saved_payees']")
	WebElement btnPay;
	

	@FindBy(xpath = "//div[@id='alert_content']")
	WebElement txtAlert;
	
	
	
	public void goToAddNewPayee() {
		tabAddNewPayee.click();
	}
	public void addNewPayee(String pName,String pAddress, String pAccount, String pDetails) {
		txtbxPayeeName.sendKeys(pName);
		txtbxPayeeAddress.sendKeys(pAddress);
		txtbxAccount.sendKeys(pAccount);
		txtbxPayeeDetails.sendKeys(pDetails);
		btnAdd.click();
	}
	public void paySavedPayee(String payee, String account, String amount, String date, String desc) {
		Select s1 = new Select(selectPayee);
		s1.selectByVisibleText(payee);
		s1 = new Select(selectAccount);
		s1.selectByVisibleText(account);
		txtbxAmount.sendKeys(amount);
		txtbxDate.sendKeys(date);
		txtbxDesc.sendKeys(desc);
		btnPay.click();
	}
	public String getAlertText() {
		String actualText=txtAlert.getText();
		return actualText;
	}
}
