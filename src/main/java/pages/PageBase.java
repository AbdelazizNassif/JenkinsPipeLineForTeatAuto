package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/*class to use Selenium methods like click/sendkeys ...etc 
 * all pages will inherit from this class*/
public class PageBase {
	/*wait will be available for any child class for PageBase class 
	 * to help sync-ing the tests*/
	protected WebDriverWait wait ;
	/*constructor of the class*/
	public PageBase(WebDriver driver)
	{
		/*init all webelements od web page with their attributes
		 * using initElements method from PageFactory class */
		PageFactory.initElements(driver, this);
	}
	
	/*clicking on webelements function(button/link)*/
	public static void clickButtonOrLink(WebElement btn)
	{
		btn.click();
	}
	/*sending text to  webelements */
	public static void setTextElementText(WebElement textElem, String value)
	{
		textElem.clear();
		textElem.sendKeys(value);
	}
	/*selecting on an option from drop deon list by visible text*/
	public static void selectCategory(WebElement catList, String visibleTxt)
	{
		Select select;
		select = new Select(catList);
		select.selectByVisibleText(visibleTxt);
	}
	/*scrolling to certain web element on the DOM*/
	public static void scrollToElement(WebDriver dvr,  WebElement webElm)
	{
		/*Casting WebDriver to JavascriptExecutor to execute javaScript code*/
		JavascriptExecutor js = (JavascriptExecutor) dvr;
		/*getting the location of the web element to scroll to it*/
		Point loc = webElm.getLocation();
		int x;
		int y;
		/*generating the x and y of the location*/
		x = loc.getX();
		y = loc.getY();
		/*scroll to the element*/
		js.executeScript("window.scrollBy(" + x + "," + y + ")" );
	}
	
	

}
