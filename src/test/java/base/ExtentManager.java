package base;

import java.io.FileInputStream;
import java.util.Properties;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			try {
				Properties prop = new Properties();
				FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
				prop.load(fis);
				String ExtentReportsFile = prop.getProperty("ExtentReportsFile");
				//System.out.println(ExtentReportsFile);
				//extent = new ExtentReports();
				extent = new ExtentReports(ExtentReportsFile, true);
				extent
                .addSystemInfo("Environment", "Automation Testing")
                .addSystemInfo("User Name", "Amandeep Brar");
				//System.out.println("ExtentReport created.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return extent;
	}
}
