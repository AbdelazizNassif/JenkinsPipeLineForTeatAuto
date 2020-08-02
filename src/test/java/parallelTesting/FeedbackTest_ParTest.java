package parallelTesting;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FeedbackPage;
import pages.HomePage;

public class FeedbackTest_ParTest extends TestBaseForGrid {
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
		getDriver().navigate().to("https://www.otlob.com/egypt?gclid=EAIaIQobChMI49nyueiD6gIViLUYCh2xRwJEEAAYASAAEgIOx_D_BwE");
		homePageObj = new HomePage(getDriver());
		feedbackPageObj = new FeedbackPage(getDriver());
		homePageObj.clickFeedBackLink(getDriver());
		Assert.assertEquals(getDriver().getCurrentUrl(), "https://www.otlob.com/egypt/feedback");
	}


	
	/*Test-> filling the feedback form test without selecting the reCaptcha
	 * Test ID-> Otlb_fedBck_003*/
	@Test(priority = 5, alwaysRun = true)
	public void invalidFeedbackForm()
	{
		feedbackPageObj.fillFeedBackFormWithoutRecaptcha(getDriver());
		Assert.assertTrue(feedbackPageObj.isCapNotSelectedMsgDisplayed());
	}
	@AfterClass
	public void endThisClassTests()
	{
		
	}
	
}
