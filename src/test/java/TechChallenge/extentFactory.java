package TechChallenge;

import com.relevantcodes.extentreports.ExtentReports;


public class extentFactory {
	public static ExtentReports Instance() {
	
	ExtentReports	extent;
	String path=System.getProperty("user.dir")+ "/test-output/ExtentReportResults.html";
	extent =new ExtentReports(path,false);
	extent.addSystemInfo("Host Name", "Local Host");
	extent.addSystemInfo("User Name", "Sayani");

	return extent;

}
}
	

	