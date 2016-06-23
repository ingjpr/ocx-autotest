/**
 * Product POM, it models landing box page from beautycon
 * @autor: JPR
 */
package poCheckout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import appModules.Waits;

public class Box {
	
	final WebDriver driver;
	
	/**Web Elements*/
	/**Common**/
	@FindBy(how = How.ID, using = "seasonal-btn")
	private static WebElement buttonSeasonal;
	
	@FindBy(how = How.ID, using = "annual-btn")
	private static WebElement buttonAnnual;
	
	@FindBy(how = How.CSS, using = "button.box-subscribe-btn")
	private static WebElement buttonSubscribe;
	
	
	/**Constructor*/
	public Box(WebDriver driver) {	 
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		if (!Waits.wait_url_contain(driver, 5, "/beautycon-box-2")) {
            throw new IllegalStateException("This is not the beautyconbox page");
        }
	}
	
	public Box selectSeasonal(){
		buttonSeasonal.click();
		return this;
	}
	
	public Box selectAnnual(){
		buttonAnnual.click();
		return this;
	}
	public Product goToSubscribe(){
		Waits.wait_for_a_bit(1000);		
		buttonSubscribe.click();
		return new Product(driver);
	}
	
}
