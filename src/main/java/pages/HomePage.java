package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*Class to contain 
 * 1 - HomePage web elements
 * 2 - Methods to manipulate these web elements
 * 3 - Methods to generate test steps for the Test so that the tests will be written 
 * 		in a specific format*/
public class HomePage extends PageBase{
	/**/
	public HomePage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver,  10);
	}
	
	/*1 - HomePage web elements*/
	@FindBy(xpath = "//section[@class='footerlinks p-y-lg hidden-xs']//a[@href='/egypt/feedback' and contains(text(), 'Feedback')]")
	private static WebElement feedBackLink;
	
	@FindBy(xpath = "//section[@class='footerlinks p-y-lg hidden-xs']//a[@href='/egypt/career' and contains(text(), 'Careers')]")
	private static WebElement careersLink;
	
	@FindBy(xpath = "//section[@class='footerlinks p-y-lg hidden-xs']//a[@href='/egypt/contact-us' and contains(text(), 'Contact Us')]")
	private static WebElement contactUsLink;
	
	@FindBy(xpath = "//li[@class='nav-list-li']//a[@class='nav-link artext' and contains(text(), 'العربية')]")
	private static WebElement arabicLanguageLink;
	
	@FindBy(xpath = "//h1[contains(text(), 'اطلب الطعام اون لاين في مصر')]")
	public WebElement arabicLanguageIndication;
	
	@FindBy(xpath = "//li[@class = 'nav-list-li']//a[@class='nav-link artext' and contains(text(), 'English')]")
	private static WebElement englishLanguageLink;
	
	@FindBy(xpath = "//h1[contains(text(), 'Order food online in Egypt')]")
	public WebElement englishLanguageIndication;
	
	/*2 - Methods to manipulate the home page web elements*/
	public void clickFeedBackLink(WebDriver dvr)
	{
		scrollToElement(dvr, feedBackLink);
		clickButtonOrLink(feedBackLink);
		wait.until(ExpectedConditions.urlContains("feedback"));
	}
	public void clickCareersLink(WebDriver dvr)
	{
		scrollToElement(dvr, careersLink);
		clickButtonOrLink(careersLink);
		wait.until(ExpectedConditions.urlContains("career"));
	}
	public void clickContactUsLink(WebDriver dvr)
	{
		scrollToElement(dvr, contactUsLink);
		clickButtonOrLink(contactUsLink);
		wait.until(ExpectedConditions.urlContains("contact"));
	}
	/*3 - Methods to generate test steps for the Test so that the tests will be written 
	 * 		in a specific format*/
	public void clickArabicLanguageLink()
	{
		wait.until(ExpectedConditions.elementToBeClickable(arabicLanguageLink));
		clickButtonOrLink(arabicLanguageLink);
	}
	public boolean isLanguageChangedToArabic()
	{
		wait.until(ExpectedConditions.visibilityOf(arabicLanguageIndication));
		return arabicLanguageIndication.isDisplayed();
	}
	public void clickEnglishLanguageLink()
	{
		wait.until(ExpectedConditions.visibilityOf(englishLanguageLink));
		clickButtonOrLink(englishLanguageLink);
	}
	public boolean isLanguageChangedToEnglish()
	{
		wait.until(ExpectedConditions.visibilityOf(englishLanguageIndication));
		return englishLanguageIndication.isDisplayed();
	}

}
