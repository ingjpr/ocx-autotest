package testLibrary;


import poCheckout.Beautycon;
import poCheckout.Box;
import poCheckout.Complete;
import poCheckout.Form;
import poCheckout.OrderSummary;
import poCheckout.Product;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

public class SmokeTestRemoteConfig {
	WebDriver driver;
	

	/**POM Involved**/
	Product product;
	OrderSummary orderSummary;
	Form form;
	Complete complete;
	Beautycon beautycon;
	Box box;
	
	/**Variables of test*/
	String email = appModules.RandomUtil.generate_email("mailinator");
	String pwd = "Grcweb123";
	String pwdconfirm = "Grcweb123";
	String phone = "3055555555";
	Boolean age = true;
	
	String[] cc = appModules.RandomUtil.pick_random_cc();
	String[] exp = appModules.RandomUtil.generate_expiration_date();
				
	String firstname = "fn"+appModules.RandomUtil.generate_unique_text();
	String lastname = "O'connor "+appModules.RandomUtil.generate_unique_text();
	String address1 = appModules.RandomUtil.generate_address("address1");
	String address2 = appModules.RandomUtil.generate_address("address2");
	String[] zip = appModules.RandomUtil.pick_random_zip();
	String city = appModules.RandomUtil.generate_random_text(8);
	
	String firstname_s = "fns"+appModules.RandomUtil.generate_unique_text();
	String lastname_s = "lns"+appModules.RandomUtil.generate_unique_text();
	String address1_s = appModules.RandomUtil.generate_address("address1");
	String address2_s = appModules.RandomUtil.generate_address("address2");
	String[] zip_s = appModules.RandomUtil.pick_random_zip();
	String city_s = appModules.RandomUtil.generate_random_text(8);
	
	String[] promocode = appModules.RandomUtil.pick_promocode();
	
	String seasonalvalue = "$29.00";
	
	String subtotal;
	String shipping;
	String total;
	
	@Test (description = "Select seasonal product", enabled = true, priority = 1)
	public void verifyProductPrice() {
		String pricedefault = orderSummary.getSummaryValuesMap().get("Subtotal");
		product.selectAProduct("Seasonal");
		String priceselected = orderSummary.getSummaryValuesMap().get("Subtotal");
		Assert.assertFalse(pricedefault.equalsIgnoreCase(priceselected), "Price is the same");
		Assert.assertTrue(priceselected.equalsIgnoreCase(seasonalvalue), "Price Not Matching, expected: "+seasonalvalue+" Obtained: "+priceselected);
	}
	
	@Test (description="Fill the account data in the form",enabled = true, priority = 2)
	public void fillAccountForm() {
		form.fillForm(email, pwd, pwdconfirm, phone, age);
		Reporter.log(email+"-"+pwd);
	}
	
	@Test (description="Fill the payment information in the form",enabled = true, priority = 3)
	public void fillPaymentForm() {
		form.fillCC(cc[1]);
		form.fillExpirationDate(exp[0],exp[1]);
		Reporter.log(cc[1]+"-"+exp[0]+"/"+exp[1]);
	}
	
	@Test (description="Fill the billing information in the form", enabled = true, priority = 4)
	public void fillBillingForm() {
		form.fillFirstNameB(firstname);
		form.fillLastNameB(lastname);
		form.fillAddress1B(address1);
		form.fillAddress2B(address2);
		form.fillZipB(zip[0]);
		form.fillCityB(city);
		Reporter.log(firstname+"-"+lastname+"-"+address1+"-"+address2+"-"+zip[0]+"-"+city);
	}
	
	@Test (description="Fill the shipping information in the form ",enabled = true, priority = 5)
	public void fillShippingForm() {
		form.clickOnShipping();
		form.fillFirstNameS(firstname_s);
		form.fillLastNameS(lastname_s);
		form.fillAddress1S(address1_s);
		form.fillAddress2S(address2_s);
		form.fillZipS(zip_s[0]);
		form.fillCityS(city_s);
		Reporter.log(firstname_s+"-"+lastname_s+"-"+address1_s+"-"+address2_s+"-"+zip_s[0]+"-"+city_s);
	}
	
	@Test (description = "Apply promo code", enabled = true, priority = 6)
	public void applyPromoCode() {
		total = orderSummary.getSummaryValuesMap().get("Total").replace("$", "");
		if(promocode[0].equalsIgnoreCase("SUMMER50")){
			product.selectAProduct("Seasonal");
		}
		orderSummary.applyPromoCode(promocode[0]);
		String total_changed = orderSummary.getSummaryValuesMap().get("Total").replace("$", "");
		String message = orderSummary.readPromoCodeText();
		Assert.assertTrue(Float.valueOf(total) > Float.valueOf(total_changed), "Price did not decrease after promo code applied");
		Reporter.log(promocode[0]+"-"+message);
	}
	
	@Test (description="Accept the terms and conditions and complete the order",enabled = true, priority = 7)
	public void completeTheOrder() {
		subtotal = orderSummary.getSummaryValuesMap().get("Subtotal");
		shipping = orderSummary.getSummaryValuesMap().get("Shipping");
		total = orderSummary.getSummaryValuesMap().get("Total");
		form.clickOnTerms();
		complete = form.completeOrder();
		/*Added to test duplicated orders it will fail this TEST*/
		complete = form.completeOrder();
		complete = form.completeOrder();
		String subtotal_completed = complete.getSummaryValuesMap().get("Subtotal");
		Assert.assertEquals(subtotal_completed, subtotal, "Expected:"+subtotal+" Obtained:"+subtotal_completed);
	}
	
	@BeforeTest
    @Parameters({"browser","size"})
    public void setup(String browser, String size) throws Exception{
		driver = appModules.Config.config(browser, size);
    }

	@BeforeClass
	@Parameters({"env"})
	public void beforeClass(String env) {
		/*Setup the environment*/
		String URL = "";
		URL = appModules.Config.environment(env, driver);
		
		/*Open the page*/
		driver.get(URL);
		driver.get(driver.getCurrentUrl());
		
		/*Initialize the needed objects*/
		product = new Product(driver);
		orderSummary = new OrderSummary(driver);
		form  = new Form(driver);
	 }
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
