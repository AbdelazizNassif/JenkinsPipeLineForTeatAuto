package tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.FeedbackPage;
import pages.HomePage;

public class FeedbackTest extends TestBase{
	/*Pages refrences to PagesClass Objects to be used in tests*/
	HomePage homePageObj;
	FeedbackPage feedbackPageObj;

	/*method to start before starting those class tests to make the following:
	 * 1 - getting the test data
	 * 2 - initializing all the references for Pages with the correct Objects
	 * 3 - navigating to the part of the website to be tested */
	@BeforeClass
	public void navigateToFeedBack()
	{
		driver.navigate().to("https://www.otlob.com/egypt?gclid=EAIaIQobChMI49nyueiD6gIViLUYCh2xRwJEEAAYASAAEgIOx_D_BwE");
		homePageObj = new HomePage(driver);
		feedbackPageObj = new FeedbackPage(driver);
		homePageObj.clickFeedBackLink(driver);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.otlob.com/egypt/feedback");
	}


	
	/*Test-> filling the feedback form test without selecting the reCaptcha
	 * Test ID-> Otlb_fedBck_003*/
	@Test(priority = 6, alwaysRun = true)
	public void invalidFeedbackForm()
	{
		feedbackPageObj.fillFeedBackFormWithoutRecaptcha(driver);
		Assert.assertTrue(feedbackPageObj.isCapNotSelectedMsgDisplayed());
	}
	@AfterClass
	public void endThisClassTests()
	{

	}
	
}
