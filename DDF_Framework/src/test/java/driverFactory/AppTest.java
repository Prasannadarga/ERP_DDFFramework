package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.ExcelFileUtil;
import commonFunctions.FunctionLibrary;
import config.AppUtil;

public class AppTest extends AppUtil {
	String inputpath = "./FileInput/logindata.xlsx";
	String outputpath = "D:\\Live_Project\\DDF_Framework\\FileOutput\\ouput.xlsx";
	ExtentReports reports;
	ExtentTest logger;

	@Test
	public void startTest() throws Throwable {
		reports = new ExtentReports("./target/Report/login.html");

		boolean res = false;
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		int rc = xl.rowCount("login");
		Reporter.log("no of rows are::" + rc, true);
		for (int i = 1; i <= rc; i++) {
			logger = reports.startTest("validate login");
			String username = xl.getCellData("login", i, 0);
			String password = xl.getCellData("login", i, 1);
			res = FunctionLibrary.adminLogin(username, password);
			if (res) {
				logger.log(LogStatus.PASS, "login success");
				xl.setCellData("login", i, 2, "login success", outputpath);
				xl.setCellData("login", i, 3, "pass", outputpath);
			} else {
				File Screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(Screen, new File("./ScreenShot/Iteration/" + i + "LoginPage.png"));
				logger.log(LogStatus.FAIL, "login fail");
				xl.setCellData("login", i, 2, "Fail", outputpath);
				xl.setCellData("login", i, 3, "Fail", outputpath);
			}
			reports.endTest(logger);
			reports.flush();

		}
	}
}
