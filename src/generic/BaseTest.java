package generic;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest implements IAutoConst{
	public WebDriver driver;
	
	static{
		System.setProperty(CHROME_KEY,CHROME_VALUE);
		System.setProperty(GECKO_KEY,GECKO_VALUE);
	}
	
	@Parameters({"browser"})
	@BeforeMethod(alwaysRun=true)
	public void openApplication(String browser){
		if(browser.equals("chrome")){
		driver=new ChromeDriver();
		}
		else{
		 driver=new FirefoxDriver();	
		}
		String url = AUL.getProperty(SETTINGS_PATH,"URL");
		driver.get(url);
		String strITO=AUL.getProperty(SETTINGS_PATH,"ITO");
		long ITO = Long.parseLong(strITO);
		driver.manage().timeouts().implicitlyWait(ITO,TimeUnit.SECONDS);
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeApplication(ITestResult res){
		String testName=res.getName();
		if(res.getStatus()==2){
			AUL.getPhoto(driver,PHOTO_PATH, testName);
		}
			
		driver.quit();
	}
}




