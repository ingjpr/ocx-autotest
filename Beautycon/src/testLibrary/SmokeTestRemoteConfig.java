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
	String invalidpromocode = "INVALIDPC";
	
	String annualvalue = "$99.00";
	String seasonalvalue = "$29.00";
	
	String subtotal;
	String shipping;
	String total;
	
	@Test (description = "Switch the product", enabled = false, priority = 0)
	public void switchProductFromDeafult() {
		String original = product.getValueSelectedProduct();
		product.switchProduct();
		String selected_a = product.getValueSelectedProduct();
		Reporter.log("Original:"+original+"-"+"Selected:"+selected_a);
		product.switchProduct();
		String selected_b = product.getValueSelectedProduct();
		Reporter.log("Original:"+original+"-"+"Selected:"+selected_b);
		Assert.assertTrue(!original.equalsIgnoreCase(selected_a), "Product did not change FROM default");
		Assert.assertTrue(original.equalsIgnoreCase(selected_b), "Product did not change TO default");
	}
	
	@Test (description = "Select seasonal product", enabled = false, priority = 1)
	public void verifyProductPrice() {
		System.out.println(product.getValueSelectedProduct());
		if(seasonalvalue.contains(product.getValueSelectedProduct())){
			product.switchProduct();
		}
		String pricedefault = orderSummary.getSummaryValuesMap().get("Subtotal");
		product.selectAProduct("Seasonal");
		String priceselected = orderSummary.getSummaryValuesMap().get("Subtotal");
		Reporter.log("Before Change:"+pricedefault+"-"+"After Change:"+priceselected);
		Assert.assertFalse(pricedefault.equalsIgnoreCase(priceselected), "Price is the same");
		Assert.assertTrue(priceselected.equalsIgnoreCase(seasonalvalue), "Price Not Matching, expected: "+seasonalvalue+" Obtained: "+priceselected);
	}
	
	@Test (description="Fill the account data in the form",enabled = false, priority = 2)
	public void fillAccountForm() {
		form.fillForm(email, pwd, pwdconfirm, phone, age);
		Reporter.log(email+"-"+pwd);
	}
	
	@Test (description="Fill the payment information in the form",enabled = false, priority = 3)
	public void fillPaymentForm() {
		form.fillCC(cc[1]);
		form.fillExpirationDate(exp[0],exp[1]);
		Reporter.log(cc[1]+"-"+exp[0]+"/"+exp[1]);
	}
	
	@Test (description="Fill the billing information in the form", enabled = false, priority = 4)
	public void fillBillingForm() {
		form.fillFirstNameB(firstname);
		form.fillLastNameB(lastname);
		form.fillAddress1B(address1);
		form.fillAddress2B(address2);
		form.fillZipB(zip[0]);
		form.fillCityB(city);
		Reporter.log(firstname+"-"+lastname+"-"+address1+"-"+address2+"-"+zip[0]+"-"+city);
	}
	
	@Test (description="Fill the shipping information in the form ",enabled = false, priority = 5)
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
	
	@Test (description = "Verify invalid promo code", enabled = true, priority = 6)
	public void applyInvalidPromoCode() {
		total = orderSummary.getSummaryValuesMap().get("Total").replace("$", "");
		orderSummary.applyPromoCode(invalidpromocode);
		String message = orderSummary.readPromoCodeError();
		String total_changed = orderSummary.getSummaryValuesMap().get("Total").replace("$", "");
		Reporter.log(invalidpromocode+"-"+message);
		Reporter.log("Before Apply:"+total+"-"+"After Apply"+total_changed);
		orderSummary.applyPromoCode("");
		Assert.assertEquals(Float.valueOf(total_changed), Float.valueOf(total), 2, "Price changed with invalid code");
		Assert.assertTrue(message.equalsIgnoreCase("The promo code is invalid"));
	}
	
	@Test (description = "Apply promo code", enabled = true, priority = 7)
	public void applyPromoCode() {
		total = orderSummary.getSummaryValuesMap().get("Total").replace("$", "");
		if(promocode[0].equalsIgnoreCase("SUMMER50")){
			product.selectAProduct("Seasonal");
		}
		orderSummary.applyPromoCode(promocode[0]);
		String message = orderSummary.readPromoCodeText();
		String total_changed = orderSummary.getSummaryValuesMap().get("Total").replace("$", "");
		Reporter.log(promocode[0]+"-"+message);
		Reporter.log("Before Apply:"+total+"-"+"After Apply"+total_changed);
		Assert.assertTrue(Float.valueOf(total) > Float.valueOf(total_changed), "Price did not decrease after promo code applied");
	}
	
	@Test (description="Accept the terms and conditions and complete the order",enabled = false, priority = 8)
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
