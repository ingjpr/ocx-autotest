package testLibraryExperiments;


import poCheckout.Form;
import poCheckout.OrderSummary;
import poCheckout.Product;


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;


public class SmokeTest {
	WebDriver driver;
	

	/**POM Involved**/
	Product product;
	OrderSummary orderSummary;
	Form form;
	
	/**Variables of test*/
	String seasonalvalue = "29.00";
	String email = appModules.RandomUtil.generate_email("mailinator");
	String pwd = "grqwe123";
	String pwdconfirm = "grqwe123";
	String phone = "3055555555";
	Boolean age = true;
	String cc = "5555555555554444";
	

	@Test (enabled = true, priority = 1)
	public void verifyProductPrice() {
		String pricedefault = orderSummary.getSummaryValuesMap().get("Subtotal");
		product.selectAProduct("Seasonal");
		String priceselected = orderSummary.getSummaryValuesMap().get("Subtotal");
		Assert.assertFalse(pricedefault.equalsIgnoreCase(priceselected), "Price is the same");
		Assert.assertTrue(priceselected.equalsIgnoreCase(seasonalvalue), "Price Not Matching");
	}
	

	@Test (enabled = true, priority = 2)
	public void fillForm() {
		form.fillForm(email, pwd, pwdconfirm, phone, age);
		form.fillCC(cc);
		form.fillExpirationDate("", "");
	}
	
	@Test (enabled = false, priority = 3)
	public void fillFieldTest() {
		form.fillFirstNameB("Juan");
		form.fillLastNameB("Rodriguez");
		form.fillAddress1B("8720 NW 33 rd street");
		form.fillAddress2B("Park Ave");
		form.fillZipB("33135");
		form.fillCityB("Doral");
	}
	
	@Test (enabled = false, priority = 4)
	public void fillFieldTest1() {
		form.clickOnShipping();
		form.fillFirstNameS("Juan");
		form.fillLastNameS("Rodriguez");
		form.fillAddress1S("8720 NW 33 rd street");
		form.fillAddress2S("Park Ave");
		form.fillZipS("33135");
		form.fillCityS("Doral");
	}
	
	@Test (enabled = false, priority = 5)
	public void fillFieldTest2() {
		//form.clickOnTerms();
		form.completeOrderFailed();
	}
	
		
	
	@BeforeTest
    @Parameters({"browser","size"})
    public void setup(String browser, String size) throws Exception{
		//Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
        //create firefox instance
        	
        	Map<String, Object> firefoxOptions = new HashMap<String, Object>();
    		firefoxOptions.put("elementScrollBehavior", "1");
        	
        	System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
        	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        	FirefoxProfile profile = new FirefoxProfile();
        	capabilities.setCapability("elementScrollBehavior", "1");
        	capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        	
        	driver =  new FirefoxDriver(capabilities);
        	
        	//capabilities.setCapability(, value);;
        	//driver = new FirefoxDriver();
           
            /*Setup Window Size*/
            switch (size){
            case "large":
            	driver.manage().window().setSize(new Dimension(1400, 800));
            	break;
            case "medium":
            	driver.manage().window().setSize(new Dimension(768, 1024));
            	break;
            case "small":
            	driver.manage().window().setSize(new Dimension(360, 640));
            	break;
            default: 
            	driver.manage().window().maximize();
            }
            
        }
        //Check if parameter passed as 'chrome'
        else if(browser.equalsIgnoreCase("chrome")){
            //set path to chromedriver.exe
        	System.setProperty("webdriver.chrome.driver", "/Users/juanrodriguez/Documents/Automation/chromedriver");
    		Map<String, String> mobileEmulation = new HashMap<String, String>();
    		mobileEmulation.put("deviceName", "Google Nexus 5");
    		
    		Map<String, Object> chromeOptions = new HashMap<String, Object>();
    		chromeOptions.put("binary", "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
    		
    		/*Setup Window Size*/
    		List<String> args = new ArrayList<String>();
            switch (size){
            case "large":
            	args.add("window-size=1400,800");
            	break;
            case "medium":
            	args.add("window-size=768,1024");
            	break;
            case "small":
            	args.add("window-size=360,640");
            	break;
            default: 
            	args.add("start-fullscreen");
            }
    	
    		chromeOptions.put("args", args);
    		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    		   		
    		driver = new ChromeDriver(capabilities);
        } else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
    }


	@BeforeClass
	public void beforeClass() {
		String user = "BeautyconSecure";
		String pwd = "Zaga2016";
		
		String URL = "http://" + user + ":" + pwd + "@" + "ocxdev.beautycon.com/checkout";
		driver.get(URL);
		driver.get(driver.getCurrentUrl());
		product = new Product(driver);
		orderSummary = new OrderSummary(driver);
		form  = new Form(driver);
	 }
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
