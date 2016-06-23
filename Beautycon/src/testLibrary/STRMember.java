package testLibrary;


import poMemberSite.Login;
import poMemberSite.MySubscription;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class STRMember {
	WebDriver driver;
	

	/**POM Involved**/
	Login login;
	MySubscription mysubscription;

	
	/**Variables of test*/
	String email = "Robot060116181739@mailinator.com";
	String pwd = "grqwe123";
	
	@Test (description = "Login", enabled = true, priority = 1)
	public void login() {
		mysubscription = login.loginSuccessfull(email, pwd);
	}
	
	@Test (description="Read Data",enabled = true, priority = 2)
	public void readOrderSummary() {
		System.out.println(mysubscription.readProductName());
		System.out.println(mysubscription.readOrderSummary().get("Billing Address"));

	}
	
	
	@BeforeTest
    @Parameters({"browser","size"})
    public void setup(String browser, String size) throws Exception{
		//Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
        //create firefox instance
        	
        	System.setProperty("webdriver.firefox.bin", "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
        	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        	FirefoxProfile profile = new FirefoxProfile();
        	capabilities.setCapability("elementScrollBehavior", "1");
        	capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        	
        	driver =  new RemoteWebDriver(capabilities);
        	
           
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
    		//Map<String, String> mobileEmulation = new HashMap<String, String>();
    		//mobileEmulation.put("deviceName", "Google Nexus 5");
    		
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
    		   		
    		driver = new RemoteWebDriver(capabilities);
        } else if(browser.equalsIgnoreCase("safari")){
            //set path to chromedriver.exe
        	//System.setProperty("webdriver.chrome.driver", "/Users/juanrodriguez/Documents/Automation/chromedriver");
    		//Map<String, String> mobileEmulation = new HashMap<String, String>();
    		//mobileEmulation.put("deviceName", "Google Nexus 5");
    		
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
    		   		
    		driver = new RemoteWebDriver(capabilities);
        } 
        else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
    }


	@BeforeClass
	@Parameters({"env"})
	public void beforeClass(String env) {
		String user = "BeautyconSecure";
		String pwd = "Zaga2016";
		String URL = "";
		
		switch(env){
		case "dev":
			URL = "http://" + user + ":" + pwd + "@" + "beautycon.dev-oceanxacsf.acsitefactory.com/user";
			break;
		case "stg":
			URL = "http://" + user + ":" + pwd + "@" + "beautycon.test-oceanxacsf.acsitefactory.com/user";
			break;
		case "prd":
			URL = "https://ocx.beautycon.com/user";
			break;
		}
		
		driver.get(URL);
		//driver.get(driver.getCurrentUrl());
		login = new Login(driver);

	 }
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
