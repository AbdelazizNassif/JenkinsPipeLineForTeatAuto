package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*Class to contain 
 * 1 - Careers Page web elements
 * 2 - Methods to manipulate these web elements
 * 3 - Methods to generate test steps for the Test so that the tests will be written 
 * 		in a specific format*/
public class CareersPage extends PageBase {

	public CareersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		wait = new WebDriverWait(driver,  10);
	}

	
	
	/*1 - Careers Page web elements*/
	@FindBy(xpath = "//input[@id='firstname']")
	private static WebElement firstNameBox;

	@FindBy(xpath = "//input[@id='lastname']")
	private static WebElement lastNameBox;

	@FindBy(xpath = "//input[@id='jobtitle']")
	private static WebElement jobTitleBox;

	@FindBy(xpath = "//input[@id='mobile']")
	private static WebElement mobileNumberBox;

	@FindBy(xpath = "//input[@id='email']")
	private static WebElement emailBox;

	@FindBy(xpath = "//input[@id='cvfile']")
	private static WebElement cvUploader;

	@FindBy(xpath = "//button[@class='btn p-x-lg green text-uppercase' and contains(text(),'Submit' )]")
	private static WebElement submitButn;

	@FindBy(xpath = "//button[@class='btn p-x-lg secondory-btn text-uppercase secondory-btn' and contains(text(),'Clear' )]")
	private static WebElement clearButn;

	@FindBy(xpath = "//label[@id='cvfile-error' and contains(text(), 'This field is required.')]")
	private static WebElement cvRequiredErrMsg;

	@FindBy(xpath = "//label[@id='lastname-error' and contains(text(), 'This field is required.')]")
	private static WebElement lastNameErrMsg;

	@FindBy(xpath = "//label[@id='jobtitle-error' and contains(text(), 'This field is required.')]")
	private static WebElement jobTilteErrMsg;

	@FindBy(xpath = "//label[@id='mobile-error' and contains(text(), 'This field is required.')]")
	private static WebElement mobileNumErrMsg;

	@FindBy(xpath = "//label[@id='email-error' and contains(text(), 'This field is required.')]")
	private static WebElement emailErrMsg;

	/*2 - Methods to manipulate these web elements*/
	private static void fillFirstName(String fName)
	{
		setTextElementText(firstNameBox, fName);
	}
	private static void fillLastName(String lName)
	{
		setTextElementText(lastNameBox, lName);
	}
	private static void fillJobTitle(String jobTitle)
	{
		setTextElementText(jobTitleBox, jobTitle);
	}
	private static void fillMobileNumber(String mobileNumber)
	{
		setTextElementText(mobileNumberBox, mobileNumber);
	}
	private static void fillEmail(String email)
	{
		setTextElementText(emailBox, email);
	}

	private static void uploadCvFile()
	{
		String cvFileName = "emptyCv.docx" ;
		String cvFileLocation = System.getProperty("user.dir") + "//uploads//" + cvFileName ;
		cvUploader.sendKeys(cvFileLocation);
	}

	private static void submitCareersForm()
	{
		clickButtonOrLink(submitButn);
	}
	private static void clearCareersForm()
	{
		clickButtonOrLink(clearButn);
	}

	/*3 - Methods to generate test steps for the Test so that the tests will be written*/
	private static void fillFormDataBoxes(String fName, String lName, String mobileNum, String email, String jobTitle)
	{
		fillFirstName(fName);
		fillLastName(lName);
		fillJobTitle(jobTitle);
		fillMobileNumber(mobileNum);
		fillEmail(email);
	}

	public void fillinValidCareersForm(String fName, String lName, String mobileNum, String email, String jobTitle)							
	{
		fillFormDataBoxes(fName, lName, mobileNum, email, jobTitle);
		submitCareersForm();
		wait.until(ExpectedConditions.visibilityOf(cvRequiredErrMsg));
	}
	public void fillValidCareersForm(String fName, String lName, String mobileNum, String email, 
			String jobTitle)							
	{
		fillFormDataBoxes(fName, lName, mobileNum, email, jobTitle);
		uploadCvFile();
		submitCareersForm();
		wait.until(ExpectedConditions.urlContains(fName)); 
	}

	public void fillCareersFormThenClearIt(String fName, String lName, String mobileNum, String email, 
			String jobTitle)							
	{
		fillFormDataBoxes(fName, lName, mobileNum, email, jobTitle);
		uploadCvFile();
		clearCareersForm();
		submitCareersForm();
		List<WebElement> allErrorFlags = new ArrayList<WebElement>();
		allErrorFlags.add(lastNameErrMsg);
		allErrorFlags.add(emailErrMsg);
		allErrorFlags.add(jobTilteErrMsg);
		allErrorFlags.add(mobileNumErrMsg);
		wait.until(ExpectedConditions.visibilityOfAllElements(allErrorFlags)); 
	}
	public void submitEmptyCareerForm()							
	{
		clearCareersForm();
		submitCareersForm();
		List<WebElement> allErrorFlags = new ArrayList<WebElement>();
		allErrorFlags.add(lastNameErrMsg);
		allErrorFlags.add(emailErrMsg);
		allErrorFlags.add(jobTilteErrMsg);
		allErrorFlags.add(mobileNumErrMsg);
		wait.until(ExpectedConditions.visibilityOfAllElements(allErrorFlags)); 
	}
	public boolean isJobTitRequiredErrMsgDisplayed()
	{
		wait.until(ExpectedConditions.visibilityOf(jobTilteErrMsg));
		return jobTilteErrMsg.isDisplayed();
	}
	public boolean isMobileNumRequiredErrMsgDisplayed()
	{
		wait.until(ExpectedConditions.visibilityOf(mobileNumErrMsg));
		return mobileNumErrMsg.isDisplayed();
	}
	public boolean isEmailRequiredErrMsgDisplayed()
	{
		wait.until(ExpectedConditions.visibilityOf(emailErrMsg));
		return emailErrMsg.isDisplayed();
	}
	public boolean isLastNameRequiredErrMsgDisplayed()
	{
		wait.until(ExpectedConditions.visibilityOf(lastNameErrMsg));
		return lastNameErrMsg.isDisplayed();
	}
	public boolean isCvRequiredErrMsgDisplayed()
	{
		wait.until(ExpectedConditions.visibilityOf(cvRequiredErrMsg));
		return cvRequiredErrMsg.isDisplayed();
	}

}
