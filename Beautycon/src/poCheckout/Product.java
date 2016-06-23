/**
 * Product POM, it models the subscription selector
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

public class Product {
	
	final WebDriver driver;
	
	/**Web Elements*/
	/**Common**/
	@FindBy(how = How.ID, using = "sas_pdp_11")
	private static WebElement buttonAnnual;
	
	@FindBy(how = How.ID, using = "sas_pdp_6")
	private static WebElement buttonSeasonal;
	
	@FindBy(how = How.CSS, using = "section.product-select")
	private static WebElement areaProduct;
		
	/**Constructor*/
	public Product(WebDriver driver) {	 
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		Waits.wait_visible(areaProduct, driver, 10);
		if (!areaProduct.isDisplayed()) {
            throw new IllegalStateException("This page does not contain the Product Area");
        }
	}

	/**
	 * Select a product based on desired by the tester 
	 * @param product : Annual or Seasonal
	 * @return
	 */
	public Product selectAProduct(String product){
		switch (product){
		case "Annual": 	
			Waits.wait_clickable(buttonAnnual, driver, 5);
			buttonAnnual.click();
			Waits.wait_for_a_bit(5000);
			break;
		case "Seasonal": 
			Waits.wait_clickable(buttonSeasonal, driver, 5);
			buttonSeasonal.click();
			Waits.wait_for_a_bit(5000);
			break;
		}
		return this;
	}
	
}
