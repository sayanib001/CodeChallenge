package TechChallenge;


import java.io.File;
import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageObjects.landingPage;
import resources.base;

public class userStory3 extends base{
	
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void setUp() {
	extent = extentFactory.Instance();
	extent.addSystemInfo("Host Name", "Local Host");
	extent.addSystemInfo("User Name", "Sayani");
	extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));

	}


	@Test

	public void uploadCsvFile() throws IOException, InterruptedException {
		test = extent.startTest("uploadCsvFile");
	    getBrowser("http://167.99.65.170");
		driver.manage().window().fullscreen();
		Thread.sleep(2000);
		landingPage l=new landingPage(driver);
		System.out.println(System.getProperty("user.dir"));
		l.browseButton().sendKeys(System.getProperty("user.dir")+".\\src\\main\\java\\resources\\testdata.csv");
		test.log(LogStatus.PASS, "Document uploaded");
		Thread.sleep(2000);
		driver.quit();
		extent.endTest(test);

	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
	if (result.getStatus() == ITestResult.FAILURE) 
	{
	test.log(LogStatus.FAIL, result.getThrowable());
	}
	extent.endTest(test);
	}

	@AfterClass
	public void tearDown() {
		extent.flush();
	}



}
