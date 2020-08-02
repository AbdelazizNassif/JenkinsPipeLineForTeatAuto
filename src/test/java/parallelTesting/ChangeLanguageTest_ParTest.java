package parallelTesting;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import tests.TestBase;

public class ChangeLanguageTest_ParTest extends TestBaseForGrid {
	/*Pages refrences to PagesClass Objects to be used in tests*/
	HomePage homePageObj;	
	
	/*method to start before starting those class tests to make the following:
	 * 1 - getting the test data
	 * 2 - initializing all the references for Pages with the correct Objects
	 * 3 - navigating to the part of the website to be tested */
	@BeforeClass
	public void navigateToHomePage()
	{
		getDriver().navigate().to("https://www.otlob.com/egypt?gclid=EAIaIQobChMI49nyueiD6gIViLUYCh2xRwJEEAAYASAAEgIOx_D_BwE");
		homePageObj = new HomePage(getDriver());
	}
	
	/*Test-> pressing العربية from the bar top-right of the page
	 * Test ID-> Otlb_HmPg_005*/
	@Test(priority = 3, alwaysRun = true)
	public void convertToArabicLanguage()
	{
		
		homePageObj.clickArabicLanguageLink();
		Assert.assertTrue(homePageObj.isLanguageChangedToArabic());
	}
	/*Test-> pressing English from the bar top-left of the page
	 * Test ID-> Otlb_HmPg_006*/
	@Test(priority = 4, dependsOnMethods = {"convertToArabicLanguage"})
	public void convertToEnglishLanguage()
	{
		
		homePageObj.clickEnglishLanguageLink();
		Assert.assertTrue(homePageObj.isLanguageChangedToEnglish());
	}
	@AfterClass
	public void endThisClassTests()
	{
		//driver.close();
	}
}
