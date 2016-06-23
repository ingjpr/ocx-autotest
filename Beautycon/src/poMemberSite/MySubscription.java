package poMemberSite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import appModules.Waits;

public class MySubscription {
	
final WebDriver driver;
	
	/**Web Elements*/
	/**Common**/
	@FindBy(how = How.CSS, using = "h3.kit-title")
	private static WebElement textProduct;
	
	@FindBys({@FindBy(how = How.CSS, using = "section.order-summary>div:nth-child(n)"), @FindBy(how = How.TAG_NAME, using = "p")})
	private static List<WebElement> textsOrderSummaryTitles;
	
	@FindBys({@FindBy(how = How.CSS, using = "section.order-summary>div:nth-child(n)"), @FindBy(how = How.TAG_NAME, using = "h3")})
	private static List<WebElement> textsOrderSummaryContent;
	
	/**Constructor*/
	public MySubscription(WebDriver driver) {	 
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		if (!Waits.wait_url_contain(driver, 5, "/user/my_subscription")) {
            throw new IllegalStateException("This is not the My Subscription page");
        }
	}
	
	public String readProductName(){
		return textProduct.getText();
	}
	
	public Map<String, HashMap<String, Integer>> readOrderSummary(){
		Map<String, HashMap<String,Integer>> orderSummaryTitles = new HashMap<String, HashMap<String,Integer>>();
		HashMap<String,Integer> temp = new HashMap<String,Integer>();
		for(int i = 0; i < textsOrderSummaryTitles.size();i++){
			temp.put(textsOrderSummaryContent.get(i).getText(), i);
			orderSummaryTitles.put(textsOrderSummaryTitles.get(i).getText(),temp);
		}
		return orderSummaryTitles;
	}

}
