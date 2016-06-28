/**
 * This class is a set of functions to provide data input for tests
 * Author JPR
 * Date 24 Jun 2016
 */
package appModules;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import poCheckout.Box;

public class Config {
	
	public static String user = "BeautyconSecure";
	public static String pwd = "Zaga2016";
	
	public static String ff_driver = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	public static String chr_driver = "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
	/*if windows
	 * public static String ff_driver = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";
	 * public static String chr_driver = "C:\\Program Files (x86)\\Google\\Application\\chrome.exe";
	 */
	
	/**
	 * Setup the webdriver based on parameters
	 * @param browser : chrome, firefox, safari
	 * @param size : large, medium, small
	 * @return driver : WebDriver
	 * @throws Exception 
	 */
	public static WebDriver config(String browser, String size) throws Exception{
		WebDriver driver;
		//Check if parameter passed from TestNG is 'firefox'
        if(browser.equalsIgnoreCase("firefox")){
        //create firefox instance
        	
        	System.setProperty("webdriver.firefox.bin", ff_driver);
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
        	System.setProperty("webdriver.chrome.driver", chr_driver);
        	ChromeOptions chromeOptions = new ChromeOptions();
        	
        	/*Setup Window Size*/
            switch (size){
            case "large":
            	chromeOptions.addArguments("window-size=1400,800");
            	break;
            case "medium":
            	chromeOptions.addArguments("window-size=768,1024");
            	break;
            case "small":
            	chromeOptions.addArguments("window-size=360,640");
            	break;
            default: 
            	chromeOptions.addArguments("start-maximized");
            }
    	
    		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
    		capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
    		   		
    		driver = new RemoteWebDriver(capabilities);
        } else{
            //If no browser passed throw exception
            throw new Exception("Browser is not correct");
        }
        return driver;
	}
	
	/**
	 * Setup the webdriver based on parameters
	 * @param browser : chrome, firefox, safari
	 * @param size : large, medium, small
	 * @return driver : WebDriver
	 * @throws Exception 
	 */
	public static String environment(String env, WebDriver driver){
		Box box;
		String URL = "";
		
		switch(env){
		case "dev":
			URL = "http://" + user + ":" + pwd + "@" + "beautycon.dev-oceanxacsf.acsitefactory.com/checkout/";
			break;
		case "stg":
			URL = "http://" + user + ":" + pwd + "@" + "beautycon.test-oceanxacsf.acsitefactory.com/checkout/";
			break;
		case "prd":
			URL = "https://beautycon.com/beautycon-box-2";
			driver.get(URL);
			box = new Box(driver);
			box.selectSeasonal();
			box.goToSubscribe();
			URL  = driver.getCurrentUrl();
			break;
		}
		return URL;
	}
}
	
	