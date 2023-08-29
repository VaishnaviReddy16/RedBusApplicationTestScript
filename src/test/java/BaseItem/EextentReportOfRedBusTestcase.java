package BaseItem;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class EextentReportOfRedBusTestcase {
	
	
	public static ExtentReports extentReport() {
		String path=System.getProperty("user.dir")+"\\reports\\RedBusReport.html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setDocumentTitle("RedBus Website Report");
		report.config().setReportName("Booking TSRTC BusSeat");
			
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Vaishnavi");
		return extent;
	}
}