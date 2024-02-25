package commonFunctions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Reporter;

import config.AppUtil;

public class FunctionLibrary extends AppUtil {
public static boolean adminLogin(String user,String pass) throws Throwable
{
	driver.get(pro.getProperty("Url"));
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.findElement(By.xpath(pro.getProperty("objReset"))).click();
	driver.findElement(By.xpath(pro.getProperty("objUser"))).sendKeys(user);
	driver.findElement(By.xpath(pro.getProperty("objPass"))).sendKeys(pass);
	driver.findElement(By.xpath(pro.getProperty("objLogin"))).click();
	Thread.sleep(4000);
	String Expected = "dashboard";
	String Actual = driver.getCurrentUrl();
	if(Actual.contains(Expected))
	{
		driver.findElement(By.xpath(pro.getProperty("objlogout"))).click();
		Reporter.log("Login Succes::"+Expected+"  "+Actual,true);
		return true;
	}
	else
	{
		//capture error message
		String errormessage = driver.findElement(By.xpath(pro.getProperty("objErrormessage"))).getText();
		Reporter.log(errormessage+"  "+Expected+"  "+Actual,true);
		driver.findElement(By.xpath(pro.getProperty("objokbtn"))).click();
	return false;
	}
}
}
