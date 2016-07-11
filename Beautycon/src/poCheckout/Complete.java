/**
 * Product POM, it models the complete page
 * @autor: JPR
 */
package poCheckout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import appModules.Waits;

public class Complete {
	
	final WebDriver driver;
	
	/**Web Elements*/
	/**Common**/
	@FindBy(how = How.CSS, using = "#edit-checkout-completion-message > div:nth-child(1) > div > h2")
	private static WebElement textGreetings;
	
	@FindBy(how = How.CSS, using = "#edit-checkout-completion-message > div:nth-child(1) > div > h3")
	private static WebElement textThanks;
	
	@FindBy(how = How.CSS, using = "section.order-information>div:nth-child(1)>p")
	private static WebElement textShipping;
	
	@FindBy(how = How.CSS, using = "section.order-information>div:nth-child(2)>p")
	private static WebElement textBilling;	

	@FindBy(how = How.CSS, using = "section.order-information>div:nth-child(3)>p")
	private static WebElement textPayment;	
	
	@FindAll({@FindBy(how = How.CSS, using = "div.order-summary-item > ul > li:nth-child(n)")})
	private static List<WebElement> textsOrderSummaryPrice;
	
	@FindBy(how = How.CSS, using = ".kit-title")
	private static WebElement textProduct;
	
	
	/*
	ul.shipment-total-summary>li:nth-child(1)>p>span:nth-child(2)
	
	ul.shipment-total-summary>li:nth-child(2)>p>span:nth-child(2)
	
	ul.shipment-total-summary>li:nth-child(3)>p>span:nth-child(2)
	
	li.shipping>p>span:nth-child(2)
	
	li.total>p>span:nth-child(2)
	*/

	
	/**Constructor*/
	public Complete(WebDriver driver) {	 
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		if (!Waits.wait_url_contain(driver, 2, "/complete")) {
            throw new IllegalStateException("This is not the confirmation page");
        }
	}

	/**
	 * Read the Map of values in order summary for complete page. Keys = Order Number, Quantity, Subtotal, Shipping and Order Total
	 * The value contain the $ symbol, then for use to compute need to remove and convert in float
	 * @return Map with [key][value] 
	 */
	public Map<String, String> getSummaryValuesMap(){
		int limit = textsOrderSummaryPrice.size();
		Map<String, String> values = new HashMap<String, String>();
		for(int i=0;i<limit;i++){
			String txt = textsOrderSummaryPrice.get(i).getText();
			String[] txt_array = txt.split("\n");
			values.put(txt_array[0],txt_array[1]);
		}
		return values;
	}
	
	public String getProductValue(){
		return textProduct.getText();
	}
	
}
