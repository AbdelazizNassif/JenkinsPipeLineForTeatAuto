package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*Class to contain 
 * 1 - Contact us Page web elements
 * 2 - Methods to manipulate these web elements
 * 3 - Methods to generate test steps for the Test so that the tests will be written 
 * 		in a specific format*/
public class ContactUsPage extends PageBase{

	public ContactUsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WebDriverWait(driver, 10);
	}
	
	
	/*1 - Contact us Page web elements*/
	@FindBy(xpath="//input[@id='name']")
	private static WebElement nameBox;
	
	@FindBy(xpath="//div[@class='form-group']//input[@id='mailFrom' and @aria-required= 'true']")
	private static WebElement emailBox;
	
	@FindBy(xpath="//div[@class='form-group']//input[@id='mobile' and @placeholder = 'Mobile']")
	private static WebElement mobileBox;
	
	@FindBy(xpath="//div[@class='form-group']//textarea[@id='comments']")
	private static WebElement commnetBox;
	
	@FindBy(xpath="//div[@id='contactus']//button[@class='btn p-x-lg green text-uppercase' and contains(text(), 'Submit')]")
	private static WebElement submitButton;
	

	@FindBy(xpath="//p[@id='contactus-recaptcha-error' and contains(text(), 'Please select captcha')]")
	private static WebElement reCaptchaNotSelectedMsg;
	/*2 - Methods to manipulate the web elements*/
	private static void fillName(String fName)
	{
		setTextElementText(nameBox, fName);
	}
	private static void fillEmail(String email)
	{
		setTextElementText(emailBox, email);
	}
	private static void fillMobileNumber(String mobileNumber)
	{
		setTextElementText(mobileBox, mobileNumber);
	}
	private static void fillComment(String comment)
	{
		setTextElementText(commnetBox, comment);
	}
	private static void clickSubmit()
	{
		clickButtonOrLink(submitButton);
	}
	/*3 - Methods to generate test steps for the Test so that the tests will be written 
 * 		in a specific format*/
	
	public void fillContactUsFormWithoutRecaptcha(WebDriver loc_dvr, String fName, String email, String mobile,
			String comment)
	{
		fillName(fName);
		fillEmail(email);
		fillMobileNumber(mobile);
		fillComment(comment);
		scrollToElement(loc_dvr, submitButton);
		clickSubmit();
	}
	
	public boolean isRecaptchaNotSelectdMsg()
	{
		wait.until(ExpectedConditions.visibilityOf(reCaptchaNotSelectedMsg));
		return reCaptchaNotSelectedMsg.isDisplayed();
	}

}
