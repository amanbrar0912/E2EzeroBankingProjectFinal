package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	public BasePage(WebDriver driver1) {
		PageFactory.initElements(driver1, this);
	}

}
