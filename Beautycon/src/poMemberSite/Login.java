package poMemberSite;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import appModules.Waits;

public class Login {
	
	final WebDriver driver;
	
	/**Web Elements*/
	/**Common**/
	@FindBy(how = How.ID, using = "edit-name--2")
	private static WebElement inputEmail;
	
	@FindBy(how = How.ID, using = "edit-pass--2")
	private static WebElement inputPwd;
	
	@FindBy(how = How.ID, using = "edit-submit--2")
	private static WebElement buttonLogin;
	
	@FindBy(how = How.CSS, using = "a.ct-active")
	private static WebElement linkForgotPwd;


	/**Constructor*/
	public Login(WebDriver driver) {	 
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		if (!Waits.wait_url_contain(driver, 5, "/user")) {
            throw new IllegalStateException("This is not the Login page");
        }
	}

	public Login fillEmail(String email){
		inputEmail.click();
		inputEmail.sendKeys(email);
		return this;
	}
	
	public Login fillPwd(String pwd){
		inputPwd.click();
		inputPwd.sendKeys(pwd);
		return this;
	}
	
	public Login clickOnlogin(){
		buttonLogin.click();
		return this;
	}
	
	public Login loginFailedl(String wrongemail, String wrongpwd){
		fillEmail(wrongemail);
		fillPwd(wrongpwd);
		clickOnlogin();	
		return this;
	}
	
	public MySubscription loginSuccessfull(String validemail, String validpwd){
		fillEmail(validemail);
		fillPwd(validpwd);
		clickOnlogin();		
		return new MySubscription(driver);
	}
	
}
