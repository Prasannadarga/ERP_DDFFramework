package config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
public static Properties pro;
public static WebDriver driver;
@BeforeTest
public static void setup() throws Throwable
{
pro = new Properties();
pro.load(new FileInputStream(".//PropertyFiles/Environment.properties"));
if(pro.getProperty("Browser").equalsIgnoreCase("Chrome"))
{
	driver = new ChromeDriver();
}
else if(pro.getProperty("Browser").equalsIgnoreCase("firefox"))
{
	driver = new FirefoxDriver();
}
Reporter.log("Brower value is not Matcing",true);
}

@AfterTest
public static void tearDown()
{
	driver.quit();
}

}