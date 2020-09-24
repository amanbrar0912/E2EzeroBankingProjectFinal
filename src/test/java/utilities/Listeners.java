package utilities;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.relevantcodes.extentreports.LogStatus;
import base.BaseClass;

public class Listeners extends BaseClass implements ITestListener {
	public String messageBody;
	// ExtentTest test;
	// ExtentReports extent = ExtentManager.getInstance();
	// ThreadLocal<ExtentTest> threadSafeExtent = new ThreadLocal<ExtentTest>();

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		// System.out.println("Method : onTestStart");
		// threadSafeExtent.get().log(LogStatus.WARNING, "TestCase started");
		//test = report.startTest(result.getName().toUpperCase());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		// System.out.println("Method : onTestSuccess");
		// threadSafeExtent.get().log(LogStatus.PASS, "TestCase pass");
		//test.log(LogStatus.PASS, "TestCase pass");
		test.log(LogStatus.PASS, result.getName().toUpperCase()+" PASS");
		report.endTest(test);
		report.flush();
	}

	public void onTestFailure(ITestResult result) {
		try {
			Utilities.captureScreenshot();
		} catch (IOException e) {
			e.printStackTrace();
		}
		test.log(LogStatus.FAIL, result.getName().toUpperCase()+" Failed with exception : "+result.getThrowable());
		test.log(LogStatus.INFO, test.addScreenCapture(Utilities.screenshotPath));
		
		report.endTest(test);
		report.flush();
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		// test.log(LogStatus.SKIP, "TestCase skipped");
		test.log(LogStatus.SKIP, result.getName().toUpperCase()+" Skipped the test as the Run mode is NO");
		report.endTest(test);
		report.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		// test.log(LogStatus.WARNING, "TestCase Failed but within success percentage");

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		// System.out.println("onStart");

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		// System.out.println("onFinish");

	}

}
