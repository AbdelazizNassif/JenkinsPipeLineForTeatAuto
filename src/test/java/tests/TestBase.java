package tests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utilities.Helper;

public class TestBase 
{
	/*This driver will send commands to the browser for the automation purposes*/
	public static WebDriver driver ; 
	
	/*Any Downloaded files will be saved to this Downlods folder inside the workspace*/
	public static String downloadPath = System.getProperty("user.dir") + "\\Downloads";
	
	/*Method To set up options to the firefox browser*/
	public static FirefoxOptions firefoxOption() {
		FirefoxOptions option = new FirefoxOptions();
		option.addPreference("browser.download.folderList", 2);
		option.addPreference("browser.download.dir", downloadPath);
		option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
		option.addPreference("browser.download.manager.showWhenStarting", false);
		option.addPreference("pdfjs.disabled", true);
		return option;
	}
	/*Method To set up options to the chrome browser*/
	public static ChromeOptions chromeOption() {
		ChromeOptions options = new ChromeOptions();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default.content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", chromePrefs);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		return options;
	}
	/*Method To run only one time before the suite to do the follwing:
	 * 1 - Determine the browser used 
	 * 2 - Initialize the driver reference to the correct object and specify the location for the local driver
	 * 		that the code will interact with
	 * 3 - maximizing the window 
	 * 4 - setting implicit wait so that the browser must wait specific amount of time 
	 * 		before announcing failure*/
	@BeforeSuite
	@Parameters({"browser"})
	public void startDriver(@Optional("chrome") String browserName) throws MalformedURLException {
		if (browserName.equalsIgnoreCase("chrome")) {
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			//driver = new ChromeDriver(chromeOption());
			//setup the chromedriver using WebDriverManager
			//WebDriverManager.chromedriver().setup();

			//Create driver object for Chrome
			//driver = new ChromeDriver();
			
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setCapability("browserName", browserName);

			///chromeOptions.setCapability("browserVersion", "67");
			//chromeOptions.setCapability("platformName", "Windows XP");
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"), chromeOptions);

		}

		else if(browserName.equalsIgnoreCase("firefox")) {
			//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
			//driver = new FirefoxDriver(firefoxOption());
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("ie")) 
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver(); 
		}

		else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver(); 
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	} 
	
	/*Method will run one time after the suite to quit all the browsers' windows*/
	
	@AfterSuite
	public void stopDriver() 
	{
		driver.quit();
	}

	/* method will run once after every annotated testng method and
	 *  take screenshot the method fails and add it in the Screenshot folder */
	@AfterMethod
	public void screenshotOnFailure(ITestResult result) 
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{
			System.out.println("Failed!");
			System.out.println("Taking Screenshot....");
			Helper.captureScreenshot(driver, result.getName());
		}
	}
}
