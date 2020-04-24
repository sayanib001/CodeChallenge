package TechChallenge;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import pageObjects.landingPage;
import resources.base;


public class userStory5 extends base {
	
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeClass
	public void startReport()
	{
		extent = extentFactory.Instance();
		extent.addSystemInfo("Host Name","LocalHost");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	
	}
	@Test

	public void dispenseTax() throws IOException, InterruptedException {
		test=extent.startTest("dispenseTax");
		getBrowser("http://167.99.65.170");
		Thread.sleep(2000);
		driver.manage().window().fullscreen();
		Thread.sleep(1000);
		verifyDispenseButtonColour();
		verifyDispenseButtonText();
		verifyCashDispensed();
		driver.quit();
		extent.endTest(test);

	}
	
	@AfterClass
	public void tearDown() {
		extent.flush();
	}
	

	//Methods to perform the test
	


	public void verifyDispenseButtonColour() throws InterruptedException

	{
		landingPage l = new landingPage(driver);
		scrollTillElement(l.dispenseButton());
		String buttonColour = l.dispenseButton().getCssValue("background-color");
		String[] hexValue = buttonColour.replace("rgba(", "").replace(")", "").split(",");

		int hexValue1 = Integer.parseInt(hexValue[0]);
		hexValue[1] = hexValue[1].trim();
		int hexValue2 = Integer.parseInt(hexValue[1]);
		hexValue[2] = hexValue[2].trim();
		int hexValue3 = Integer.parseInt(hexValue[2]);

		String actualColor = String.format("#%02x%02x%02x", hexValue1, hexValue2, hexValue3);
		if (actualColor.contains("dc3545")) {
			System.out.println("Expectation matches : Button RED");
			test.log(LogStatus.PASS, "Button colour as expected");
		} else {
			System.out.println("Expectation doesnt match");
			test.log(LogStatus.FAIL, "Button colour NOT as expected");
		}

	}

	public void verifyDispenseButtonText()

	{
		landingPage l = new landingPage(driver);
		String buttonText = l.dispenseButton().getText();
		if (buttonText.equals("Dispense Now")) {
			System.out.println("Button text matches");
			test.log(LogStatus.PASS, "Button text matches as expected");
		} else {
			System.out.println("Button text doesnt match");
			test.log(LogStatus.FAIL, "Button text doesnot match");
		}
	}

	public void scrollTillElement(WebElement Element) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
		
	}
	
	
	public void verifyCashDispensed() throws InterruptedException {
		
		landingPage l = new landingPage(driver);
		l.dispenseButton().click();
		Thread.sleep(1000);
		verifyCashDispenseText();		

	}
	
	public void verifyCashDispenseText() {

		landingPage l = new landingPage(driver);
		if(l.cashDispensed().isDisplayed())
		{
			System.out.println("Cash Dispensed text exist as expected");
			test.log(LogStatus.PASS, "Cash Dispensed text exist as expected");
		}
		else
		{
			System.out.println("Cash Dispensed text not exist");
			test.log(LogStatus.FAIL, "Cash Dispensed text not exist");
		}
	}
	
	
 }
	
	
	
	


