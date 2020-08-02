package parallelTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utilities.Helper;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBaseForGrid {

	public static String baseURL = "https://www.otlob.com/egypt?gclid=EAIaIQobChMI49nyueiD6gIViLUYCh2xRwJEEAAYASAAEgIOx_D_BwE";
	

	protected ThreadLocal<RemoteWebDriver> driver = null ; 

	@BeforeClass
	@Parameters(value = {"browser"})
	public void setUp(@Optional("chrome") String browser) throws MalformedURLException 
	{
		driver = new ThreadLocal<>(); 
		DesiredCapabilities caps = new DesiredCapabilities(); 
		caps.setCapability("browserName", browser);
		
		// Selenium Grid Local
		driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps));
		
		// Run on SauceLabs on Cloud
		//driver.set(new RemoteWebDriver(new URL(sauceURL),caps));
		
		getDriver().navigate().to(baseURL);
	}

	public WebDriver getDriver() 
	{
		return driver.get(); 
	}

	@AfterClass
	public void stopDriver() 
	{
		getDriver().quit();
		driver.remove();
	}

	@AfterMethod
	public void screenshotOnFailure(ITestResult result) 
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenshot(getDriver(), result.getName());
		}
	}
}
