package parallelTesting;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactUsPage;
import pages.HomePage;
import testDataGenerator.TestDataGeneration;
import tests.TestBase;

public class ContactUsTest_ParTest extends TestBaseForGrid {
	/*Pages refrences to PagesClass Objects to be used in tests*/
	HomePage homePageObj;
	ContactUsPage contactUsObj;
	
	/*variables for Test Data*/
	private static String fName;
	private static String email;
	private static String mobile;
	private static String comment;
	
	/*method to start before starting those class tests to make the following:
	 * 1 - getting the test data
	 * 2 - initializing all the references for Pages with the correct Objects
	 * 3 - navigating to the part of the website to be tested */
	@BeforeClass
	public void navigateToContactUS()
	{
		getDriver().navigate().to("https://www.otlob.com/egypt?gclid=EAIaIQobChMI49nyueiD6gIViLUYCh2xRwJEEAAYASAAEgIOx_D_BwE");
		homePageObj = new HomePage(getDriver());
		contactUsObj = new ContactUsPage(getDriver());
		homePageObj.clickContactUsLink(getDriver());
		generateTestData();
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.otlob.com/egypt/contact-us");
	}

	/*Test-> filling the contact us form test without selecting reCaptcha
	 * Test ID-> Otlb_ContUs_002*/
	@Test(priority = 6, alwaysRun = true)
	public void invalidContactForm()
	{
		contactUsObj.fillContactUsFormWithoutRecaptcha(getDriver(), fName, email, mobile, comment);
		Assert.assertTrue(contactUsObj.isRecaptchaNotSelectdMsg());
	}
	@AfterClass
	public void endThisClassTests()
	{

	}
	
	/*generating Test Data*/
	private static void generateTestData()
	{
		TestDataGeneration testDataObj = new TestDataGeneration();
		fName = testDataObj.generateRandomName(6);
		email = testDataObj.generateRandomEmail(8);
		mobile = testDataObj.generateRandomPhoneNumber();
		comment = testDataObj.addReviewOrComment();
	}
	
}
