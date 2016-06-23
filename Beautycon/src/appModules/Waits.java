package appModules;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waits {
	public static boolean wait_clickable(WebElement element, WebDriver driver, int secs){
		try{
			WebDriverWait wait = new WebDriverWait(driver, secs);
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static boolean wait_visible(WebElement element, WebDriver driver, int secs){
		try{
			WebDriverWait wait = new WebDriverWait(driver, secs);
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		}catch(Exception e){
			System.out.println("Element Not Visible");
			return false;
		}
	}
	
	public static void wait_all_visible(List<WebElement> elements, WebDriver driver, int secs){
		WebDriverWait wait = new WebDriverWait(driver, secs);
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}
	
	public static boolean wait_invisible(By locator, WebDriver driver, int secs){
		try{
			WebDriverWait wait = new WebDriverWait(driver, secs);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public static void wait_prescense(By locator, WebDriver driver, int secs){
		WebDriverWait wait = new WebDriverWait(driver, secs);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	public static boolean wait_text_present_in_element(WebElement element, WebDriver driver, int secs, String text){
		try{
			WebDriverWait wait = new WebDriverWait(driver, secs);
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean wait_url_contain(WebDriver driver, int secs, String url){
		WebDriverWait wait = new WebDriverWait(driver, secs);
		return wait.until(ExpectedConditions.urlContains(url));
	}
	
	public static void wait_all_element(By locator, WebDriver driver, int secs){
		WebDriverWait wait = new WebDriverWait(driver, secs);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	public static boolean wait_alertPresent(WebDriver driver, int secs){
		try{
			WebDriverWait wait = new WebDriverWait(driver, secs);
			wait.until(ExpectedConditions.alertIsPresent());
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static void wait_for_a_bit(int millis){
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupt();		
		}
	}

}



