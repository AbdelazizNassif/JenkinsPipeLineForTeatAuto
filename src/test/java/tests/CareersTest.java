package tests;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import pages.CareersPage;
import pages.HomePage;
import testDataGenerator.TestDataGeneration;

public class CareersTest extends TestBase {
	/*Pages refrences to PagesClass Objects to be used in tests*/
	HomePage homePageObj;
	CareersPage careersPageObj;
	
	/*variables for Test Data*/
	private static String fname;
	private static String lname;
	private static String email;
	private static String mobileNum;
	private static String jobTitle;
	
	/*method to start before starting those class tests to make the following:
	 * 1 - getting the test data
	 * 2 - initializing all the references for Pages with the correct Objects
	 * 3 - navigating to the part of the website to be tested */
	@BeforeClass
	public void navigateToCareers()
	{
		driver.navigate().to("https://www.otlob.com/egypt?gclid=EAIaIQobChMI49nyueiD6gIViLUYCh2xRwJEEAAYASAAEgIOx_D_BwE");
		homePageObj = new HomePage(driver);
		careersPageObj = new CareersPage(driver);
		homePageObj.clickCareersLink(driver);
		generateTestData();	
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.otlob.com/egypt/career");
	}
	
	/*Test-> filling the careers form test without uploading the CV to the form
	 * Test ID-> Otlb_Career_007*/
	@Test(priority = 1, alwaysRun = true)
	public void invalidCareerForm()
	{
		
		careersPageObj.fillinValidCareersForm(fname, lname, mobileNum, email, jobTitle);
		Assert.assertTrue(careersPageObj.isCvRequiredErrMsgDisplayed());
	}
	/*Test-> filling the careers form test and uploading the CV to the form
	 * Test ID-> Otlb_Career_003*/
	@Test(priority = 2, dependsOnMethods = {"invalidCareerForm"})
	public void validCareerForm()
	{
		
		careersPageObj.fillValidCareersForm(fname, lname, mobileNum, email, jobTitle);
		Assert.assertTrue(driver.getCurrentUrl().contains(fname));
	}
	
	/*Test-> Leave the form empty and submit the form
	 * Test ID-> Otlb_Career_001*/
	@Test(priority = 3, dependsOnMethods = {"validCareerForm"})
	public void fillCareerFormThenClearIt()
	{
		
		careersPageObj.fillCareersFormThenClearIt(fname, lname, mobileNum, email, jobTitle);
		Assert.assertTrue(careersPageObj.isLastNameRequiredErrMsgDisplayed());
		Assert.assertTrue(careersPageObj.isJobTitRequiredErrMsgDisplayed());
		Assert.assertTrue(careersPageObj.isMobileNumRequiredErrMsgDisplayed());
		Assert.assertTrue(careersPageObj.isEmailRequiredErrMsgDisplayed());
		Assert.assertTrue(careersPageObj.isCvRequiredErrMsgDisplayed());
	}
	@AfterClass
	public void endThisClassTests()
	{
		
	}
	/*generating Test Data and saving the to variables*/
	private static void generateTestData()
	{
		TestDataGeneration testDataObj = new TestDataGeneration();
		fname = testDataObj.generateRandomName(6);
		lname = testDataObj.generateRandomName(6);;
		email = testDataObj.generateRandomEmail(8);
		mobileNum = testDataObj.generateRandomPhoneNumber();
		jobTitle = testDataObj.generateRandomJobTitle();
	}
}
