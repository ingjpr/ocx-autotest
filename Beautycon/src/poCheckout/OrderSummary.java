/**
 * Product POM, it models the order summary module
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

public class OrderSummary {
	
	final WebDriver driver;
	
	/**Web Elements*/
	/**Common**/
	@FindBy(how = How.ID, using = "edit-oceanx-coupons-pane-coupons-form-promo-code")
	private static WebElement inputPromoCode;
	
	@FindBy(how = How.ID, using = "edit-oceanx-coupons-pane-coupons-form-submit")
	private static WebElement buttonPromoCode;
	
	@FindBy(how = How.CSS, using = "div#edit-oceanx-coupons-pane-coupons-form>p>div")
	private static WebElement messageValidCode;
	
	@FindBy(how = How.CSS, using = "li.your-price>p>span:nth-child(2)")
	private static WebElement textSubtotalPrice;
	
	@FindBy(how = How.CSS, using = "li.shipping>p>span:nth-child(2)")
	private static WebElement textShippingPrice;
	
	@FindBy(how = How.CSS, using = "li.total>p>span:nth-child(2)")
	private static WebElement textTotalPrice;
	
	@FindBy(how = How.CSS, using = "a.btn-remove-promo-code")
	private static WebElement linkRemoveCode;
	
	@FindBy(how = How.CSS, using = "div.ct-active")
	private static WebElement textDiscount;
	
	@FindBy(how = How.CSS, using = "div#edit-oceanx-coupons-pane-coupons-form>h3>div")
	private static WebElement textPromoCode;
	
	@FindBy(how = How.CSS, using = "div.order-summary")
	private static WebElement areaOrderSummary;
	
	@FindAll({@FindBy(how = How.CSS, using = "ul.shipment-total-summary > li:nth-child(n)")})
	private static List<WebElement> textsOrderSummaryPrice;


	/**Constructor*/
	public OrderSummary(WebDriver driver) {	 
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		Waits.wait_visible(areaOrderSummary, driver, 10);
		if (!areaOrderSummary.isDisplayed()) {
            throw new IllegalStateException("This page does not contain the Order Summary area");
        }
	}

	/**
	 * Read the Map of prices in order summary: Subtotal, Shipping and Total
	 * @return Map with [key][value] 
	 */
	public Map<String, String> getSummaryValuesMap(){
		int limit = textsOrderSummaryPrice.size();
		Map<String, String> prices = new HashMap<String, String>();
		for(int i=0;i<limit;i++){
			String txt = textsOrderSummaryPrice.get(i).getText();
			String[] txt_array = txt.split("\n");
			prices.put(txt_array[0],txt_array[1]);
		}
		return prices;
	}
		
	/**
	 * Enter and apply a promocode
	 * @param promocode : receive any promocode
	 * @return
	 */
	public OrderSummary applyPromoCode(String promocode){
		inputPromoCode.click();
		inputPromoCode.sendKeys(promocode);
		buttonPromoCode.click();
		Waits.wait_visible(messageValidCode, driver, 5);
		return this;
	}
	
	/**
	 * Read the promocode message, it only will be visible if code is valid
	 * @return String with message
	 */
	public String readPromoCodeText(){
		Waits.wait_visible(messageValidCode, driver, 5);
		return messageValidCode.getText();
	}
	
}
