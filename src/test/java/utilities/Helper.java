package utilities;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/*This class will contains methods to provide service for all other classes
 * like: Taking screen shots for failed test cases*/
public class Helper {

	/*Method to take screenshot when the test cases fail*/
	public static void captureScreenshot(WebDriver driver , String screenshotname) 
	{
		/*Save the taken screenshots to Screenshots folder inside the workspace*/
		Path dest = Paths.get("./Screenshots",screenshotname+".png"); 
		try {
			Files.createDirectories(dest.getParent());
			FileOutputStream out = new FileOutputStream(dest.toString());
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (IOException e) {
			System.out.println("Excpetion while taking screenshot"+ e.getMessage());
		}
	}
}
