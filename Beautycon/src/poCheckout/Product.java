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
	
	@FindBy(how = How.CSS, using = "div#sas_pdp_11>div:nth-child(2)>span")
	private static WebElement textAnnual;
	
	@FindBy(how = How.CSS, using = "div#sas_pdp_6>div:nth-child(2)>span")
	private static WebElement textSeasonal;
	
		
	/**Constructor*/
	public Product(WebDriver driver) {	 
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 12), this);
		Waits.wait_visible(areaProduct, driver, 12);
		if (!areaProduct.isDisplayed()) {
            throw new IllegalStateException("This page does not contain the Product Area");
        }
	}
	
	/**
	 * Select annual
	 * @return
	 */
	public Product selectAnnual(){
		Waits.wait_clickable(buttonAnnual, driver, 6);
		buttonAnnual.click();
		Waits.wait_for_a_bit(5000);
		return this;
	}
	
	/**
	 * Select seasonal
	 * @return
	 */
	public Product selectSeasonal(){
		Waits.wait_clickable(buttonSeasonal, driver, 6);
		buttonSeasonal.click();
		Waits.wait_for_a_bit(5000);
		return this;
	}
	
	
	/**
	 * Select a product based on desired by the tester 
	 * @param product : Annual or Seasonal
	 * @return
	 */
	public Product selectAProduct(String product){
		switch (product){
		case "Annual": 	
			selectAnnual();
			break;
		case "Seasonal": 
			selectSeasonal();
			break;
		}
		return this;
	}
	
	/**
	 * Change the product
	 * @return
	 */
	public Product switchProduct(){
		String annual = buttonAnnual.getAttribute("class");
		String seasonal = buttonSeasonal.getAttribute("class");
		if(annual.contains("selected")){
			selectSeasonal();
		}else if(seasonal.contains("selected")){
			selectAnnual();
		}
		return this;
	}
	
	/**
	 * Get value of selected the product
	 * @return
	 */
	public String getValueSelectedProduct(){
		String annual = buttonAnnual.getAttribute("class");
		String seasonal = buttonSeasonal.getAttribute("class");
		String value = "";
		if(annual.contains("selected")){
			value = textAnnual.getText();
		}else if(seasonal.contains("selected")){
			value = textSeasonal.getText();
		}
		return value;
	}
	
}
