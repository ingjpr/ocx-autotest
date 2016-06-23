/**
 * Product POM, it models landing page form beautycon
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

public class Beautycon {
	
	final WebDriver driver;
	
	/**Web Elements*/
	/**Common**/
	@FindBy(how = How.CSS, using = "li.fm_menu_items>a")
	private static WebElement linkBox;
	
	/**Constructor*/
	public Beautycon(WebDriver driver) {	 
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		if (!Waits.wait_url_contain(driver, 5, "/beautycon-box-experience")) {
            throw new IllegalStateException("This is not the landing page");
        }
	}

	public Box goToBox(){
		linkBox.click();
		return new Box(driver);
	}
	
}
