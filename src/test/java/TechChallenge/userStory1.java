package TechChallenge;

import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.Keys;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Iterator;

import com.opencsv.CSVReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageObjects.swaggerUiPage;
import resources.base;

public class userStory1 extends base {

	ExtentReports extent;
	ExtentTest test;

	@BeforeClass
	public void setUp() {
		extent = extentFactory.Instance();
		extent.addSystemInfo("Host Name", "Local Host");
		extent.addSystemInfo("User Name", "Sayani");
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));

	}

	@Test
	public void singleRecordEntryWithAPI() throws IOException, InterruptedException

	{
		test = extent.startTest("singleRecordEntryWithAPI");
		getBrowser("http://167.99.65.170/swagger-ui.html");
		driver.manage().window().fullscreen();
		Thread.sleep(2000);
		swaggerUiPage spo = new swaggerUiPage(driver);
		spo.calculatorController().click();
		spo.calculatorInsert().click();
		spo.tryItOut().click();
		spo.enterParameter().clear();
		spo.enterParameter().sendKeys("{}");
		spo.enterParameter().sendKeys(Keys.ARROW_LEFT);
		readCSV();
		spo.executeButton().click();
		sendPOST();
		driver.quit();
		extent.endTest(test);
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		extent.endTest(test);
	}

	@AfterClass
	public void tearDown() {
		extent.flush();
	}

	public void readCSV() throws IOException, InterruptedException {
		CSVReader reader = new CSVReader(new FileReader(".\\src\\main\\java\\resources\\testdata.csv"));
		swaggerUiPage spo = new swaggerUiPage(driver);

		List<String[]> list = reader.readAll();
		System.out.println("Total rows which we have is " + list.size());
		
		

		// create Iterator reference
		Iterator<String[]> iterator = list.iterator();

		// Iterate all values
		while (iterator.hasNext()) {

			String[] str = iterator.next();
			for (int i = 0; i < str.length; i++) {
				//spo.enterParameter().click();
				spo.enterParameter().sendKeys("" + str[i] + "\r\n");
				Thread.sleep(1000);
			}
		}

	}
		

	public void sendPOST() throws IOException {

		final String POST_URL = "http://167.99.65.170/calculator/insert";

		final String POST_PARAMS = "{\n" + "\"birthday\":\"10111984\",\r\n" + "\"gender\":\"M\",\r\n"
				+ "\"name\":\"TestTest\",\r\n" + "\"natid\":\"11117892\",\r\n" + "\"salary\":\"12000.00\",\r\n"
				+ "\"tax\":\"5000.00\"" + "\n}";

		URL obj = new URL(POST_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");

		// For POST only - START
		OutputStream os = con.getOutputStream();
		os.write(POST_PARAMS.getBytes());
		os.flush();
		os.close();

		// For POST only - END

		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);

		if (responseCode == 202) { // success
			System.out.println("POST request worked");
			test.log(LogStatus.PASS, "POST request worked : Response code as expected");
		} else {
			System.out.println("POST request not worked");
			test.log(LogStatus.FAIL, "POST request NOT worked :Response code NOT as expected");
		}
	}

}
