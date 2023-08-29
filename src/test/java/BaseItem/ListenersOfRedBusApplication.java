package BaseItem;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class ListenersOfRedBusApplication extends BaseClass implements ITestListener {
	ExtentTest test;
	ExtentReports extent=EextentReportOfRedBusTestcase.extentReport();

	@Override
	public void onTestStart(ITestResult result) {
		// Your logic for when a test starts
		String extentReportFilepath=System.getProperty("user.dir")+"\\reports\\RedBusReport.html";
		File extentReportFile = new File(extentReportFilepath);
		if(extentReportFile.exists()) {
			extentReportFile.delete();
		}     
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// Your logic for when a test succeeds
		test.log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// Your logic for when a test fails
		test.fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Your logic for when a test is skipped
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Your logic for when a test fails within success percentage
	}

	@Override
	public void onStart(ITestContext context) {
		// Your logic for when test suite starts
	}

	@Override
	public void onFinish(ITestContext context) {
		// Your logic for when test suite finishes
		extent.flush();
	}
	
	}