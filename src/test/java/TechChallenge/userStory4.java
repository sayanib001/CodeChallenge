package TechChallenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageObjects.swaggerUiPage;
import resources.base;

public class userStory4 extends base {

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
	public void getResponseValidation() throws IOException, InterruptedException

	{
		test = extent.startTest("getResponseValidation");
		getBrowser("http://167.99.65.170/swagger-ui.html");
		driver.manage().window().fullscreen();
		swaggerUiPage spo = new swaggerUiPage(driver);
		spo.calculatorController().click();
		spo.getTaxRelief().click();
		spo.tryItOut().click();
		spo.executeButton().click();
		sendGet();
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

	public void sendGet() throws IOException {
		final String GET_URL = "http://167.99.65.170/calculator/taxRelief";

		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Content-Type", "application/json");
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);

			}
			in.close();

			// print result
			System.out.println(response.toString());
			test.log(LogStatus.PASS, "GET request worked : Response code as expected");
			parseJSON(response.toString());

		} else {
			System.out.println("GET request not worked");
			test.log(LogStatus.FAIL, "GETT request NOT worked : Response code NOT as expected");
		}

	}

	@SuppressWarnings("unlikely-arg-type")
	public void parseJSON(String response) {

		JSONArray jsonArr = new JSONArray(response);

		for (int i = 0; i < jsonArr.length()-1; i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i + 1);
			String k = jsonObj.keys().next();
			String value = jsonObj.getString(k);
			int natIdLength = value.length();
			String natIdSubString = value.substring(4, natIdLength);
			System.out.println(natIdSubString);
			for (int j = 0; j < natIdSubString.length() - 1; j++) {
				Character getChar = natIdSubString.charAt(j);
				String s=Character.toString(getChar);
				if (s.equals("$")) {
					test.log(LogStatus.PASS, "natid value is masked as expected");
				} else {
					test.log(LogStatus.FAIL, "natid value is not masked as expected");
				}

			}
		}
	}
}
