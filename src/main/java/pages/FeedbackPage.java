package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*Class to contain 
 * 1 - Feedback Page web elements
 * 2 - Methods to manipulate these web elements
 * 3 - Methods to generate test steps for the Test so that the tests will be written 
 * 		in a specific format*/
public class FeedbackPage extends PageBase{
	
	public FeedbackPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WebDriverWait(driver, 10);
	}
	/*1 - Feedback Page web elements*/
	@FindBy(xpath = "//span[@id='recaptcha-anchor']//div[@class='recaptcha-checkbox-border']")
	private static WebElement reCaptcha;
	

	@FindBy(xpath = "//div[@class= 'recaptcha-checkbox-checkmark']")
	private static WebElement checkMark;
	
	@FindBy(xpath = "//div[@class='exp extreme']//button[contains(text(), 'Extremely Satisfied')]")
	private static WebElement extremeSatisfac;
	
	@FindBy(xpath = "/html/body/section/div[2]/div/div[1]/section[2]/div[2]/div[2]/button")
	private static WebElement effort_2;
	
	@FindBy(xpath = "/html/body/section/div[2]/div/div[1]/section[3]/div[2]/div[9]/button")
	private static WebElement recommend_8;
	
	@FindBy(xpath = "//textarea[@id='feedback-text']")
	private static WebElement reviewBox;
	
	@FindBy(xpath = "//button[@id='submit-feedback']")
	private static WebElement submitButton;
	
	////iframe[@role='presentation']
	@FindBy(xpath = "//iframe[@role='presentation']")
	private static WebElement frameWebElem;
	
	@FindBy(xpath = "//p[@id='feedback-recaptcha-error']")
	private static WebElement capNotSelected;
	
	/*2 - Methods to manipulate these web elements*/
	private static void clickExtremSatisf()
	{
		clickButtonOrLink(extremeSatisfac);
	}
	private static void clickEffort_2()
	{
		clickButtonOrLink(effort_2);
	}
	private static void clickRecommend_8()
	{
		clickButtonOrLink(recommend_8);
	}
	private static void addReview()
	{
		setTextElementText(reviewBox, "The App is so good");
	}
	public void clickRecaptcha(WebDriver loc_dvr)
	{
		loc_dvr.switchTo().frame(frameWebElem);
		clickButtonOrLink(reCaptcha);
		wait.until(ExpectedConditions.visibilityOf(checkMark));
		loc_dvr.switchTo().defaultContent();
	}
	private static void clickSubmitButton()
	{
		clickButtonOrLink(submitButton);
	}
	/*3 - Methods to generate test steps for the Test so that the tests will be written 
 * 		in a specific format*/
	public void fillFeedBackFormWithoutRecaptcha(WebDriver loc_dvr)
	{
		clickExtremSatisf();
		clickEffort_2();
		clickRecommend_8();
		addReview();
		scrollToElement(loc_dvr, submitButton);
		clickSubmitButton();
	}
	
	public boolean isCapNotSelectedMsgDisplayed()
	{
		wait.until(ExpectedConditions.visibilityOf(capNotSelected));
		return capNotSelected.isDisplayed();
	}
	
	
	
	
	
	
	
	
	
}
