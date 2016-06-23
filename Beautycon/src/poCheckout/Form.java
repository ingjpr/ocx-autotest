/**
 * Product POM, it models the Form in checkout
 * @autor: JPR
 */
package poCheckout;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;

import appModules.RandomUtil;
import appModules.Waits;

public class Form {
	
	final WebDriver driver;
	
	/**Web Elements*/
	/**Common**/
	@FindBy(how = How.ID, using = "edit-account")
	private static WebElement formAccountInformation;
	
		@FindBy(how = How.ID, using = "edit-account-login-mail")
		private static WebElement inputEmail;
		
		    /**
		     * This email is already registered. If you want to place a new order, try with a different email address: when already email
		     */
			@FindBy(how = How.CSS, using = "div.valid-error")
			private static WebElement messageEmailRegistered;
			
			/**
			 * This field is required: when empty
			 * Please enter a valid email address: when invalid mail
			 */
			@FindBy(how = How.ID, using = "edit-account-login-mail-error")
			private static WebElement messageEmailInvalid;
					
		@FindBy(how = How.ID, using = "edit-account-password")
		private static WebElement inputPwd;
			
			/**
			 * This field is required: when empty
			 * Please enter at least 8 characters: when less than 8 characters
			 */
			@FindBy(how = How.ID, using = "edit-account-password-error")
			private static WebElement messagePwdInvalid;
		
		
		@FindBy(how = How.ID, using = "edit-account-password-confirm")
		private static WebElement inputPwdConfirm;
		
			/**
			 * This field is required: when empty
			 * Please enter at least 8 characters: when same and less than 8 characters
			 * Please enter the same value again: when different
			 */
			@FindBy(how = How.ID, using = "edit-account-password-confirm-error")
			private static WebElement messagePwdConfirmInvalid;
		

		@FindBy(how = How.ID, using = "edit-account-user-phone")
		private static WebElement inputPhone;
		
			/**
			 * This field is required: when empty
			 * Please specify a valid phone number: when more than 10 numbers or less than 10 numbers
			 */
			@FindBy(how = How.ID, using = "edit-account-user-phone-error")
			private static WebElement messagePhoneInvalid;
		
		
		@FindBy(how = How.CSS, using = "div.form-type-checkbox>label")
		private static WebElement checkboxAge;
		
			/**
			 * This field is required: when not checked off
			 */
			@FindBy(how = How.ID, using = "account[age_verification]-error")
			private static WebElement messageAgeRequired;

	@FindBy(how = How.ID, using = "edit-commerce-payment")
	private static WebElement formPaymentInformation;
		
		@FindBy(how = How.CSS, using = "input#edit-commerce-payment-payment-details-credit-card-number")
		private static WebElement inputCC;
		
		@FindBy(how = How.ID, using = "edit-commerce-payment-payment-details-credit-card-exp-month")
		private static WebElement inputExpirationMonth;
		
		@FindBy(how = How.ID, using = "edit-commerce-payment-payment-details-credit-card-exp-year")
		private static WebElement inputExpirationYear;
		
		/**Methods for select elements**/
		private static Select selectMonth = null;
		private static Select selectMonth (WebElement element){
	    	selectMonth = new Select (element);
			return selectMonth;
	    }
		
		private static Select selectYear = null;
		private static Select selectYear (WebElement element){
	    	selectYear = new Select (element);
			return selectYear;
	    }
		
	@FindBy(how = How.ID, using = "edit-customer-profile-billing>legend")
	private static WebElement formBillingInformation;
	
		@FindBy(how = How.ID, using = "edit-customer-profile-billing-commerce-customer-address-und-0-first-name")
		private static WebElement inputFirstNameB;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-billing-commerce-customer-address-und-0-last-name")
		private static WebElement inputLastNameB;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-billing-commerce-customer-address-und-0-thoroughfare")
		private static WebElement inputAddress1B;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-billing-commerce-customer-address-und-0-premise")
		private static WebElement inputAddress2B;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-billing-commerce-customer-address-und-0-postal-code")
		private static WebElement inputZipB;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-billing-commerce-customer-address-und-0-administrative-area")
		private static WebElement inputStateB;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-billing-commerce-customer-address-und-0-locality")
		private static WebElement inputCityB;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-shipping-commerce-customer-profile-copy")
		private static WebElement checkBoxShippingB;
		
	@FindBy(how = How.ID, using = "edit-customer-profile-shipping-commerce-customer-address-und-0")
	private static WebElement formShippingInformation;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-shipping-commerce-customer-address-und-0-first-name")
		private static WebElement inputFirstNameS;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-shipping-commerce-customer-address-und-0-last-name")
		private static WebElement inputLastNameS;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-shipping-commerce-customer-address-und-0-thoroughfare")
		private static WebElement inputAddress1S;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-shipping-commerce-customer-address-und-0-premise")
		private static WebElement inputAddress2S;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-shipping-commerce-customer-address-und-0-postal-code")
		private static WebElement inputZipS;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-shipping-commerce-customer-address-und-0-administrative-area")
		private static WebElement inputStateS;
		
		@FindBy(how = How.ID, using = "edit-customer-profile-shipping-commerce-customer-address-und-0-locality")
		private static WebElement inputCityS;
		
	@FindBy(how = How.ID, using = "edit-oceanx-sas-commerce-terms-pane-agree")
	private static WebElement checkBoxTermsAndCondtions;
		
	@FindBy(how = How.ID, using = "edit-continue")
	private static WebElement buttonCompleteOrder;
		
		/**Methods for select elements**/
		private static Select selectState = null;
		private static Select selectState (WebElement element){
	    	selectState = new Select (element);
			return selectState;
	    }
		
		
	/**Constructor*/
	public Form(WebDriver driver) {	 
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
		Waits.wait_visible(formAccountInformation, driver, 10);
		if (!formAccountInformation.isDisplayed()) {
            throw new IllegalStateException("This page does not contain the Order Summary area");
        }
	}

	/**
	 * Fill email
	 * @return the Form POM
	 */
	public Form fillEmail(String email) {
		inputEmail.click();
		inputEmail.sendKeys(email);
		return this;
	}
	
	/**
	 * Fill Password
	 * @return the Form POM
	 */
	public Form fillPassword(String pwd) {
		inputPwd.click();
		inputPwd.sendKeys(pwd);
		return this;
	}
	
	/**
	 * Fill Password Confirmation
	 * @return the Form POM
	 */
	public Form fillPasswordConfirm(String pwdconfirm) {
		inputPwdConfirm.click();
		inputPwdConfirm.sendKeys(pwdconfirm);
		return this;
	}
	
	/**
	 * Fill Day Phone Number
	 * @return the Form POM
	 */
	public Form fillPhone(String phone) {
		inputPhone.click();
		inputPhone.sendKeys(phone);
		return this;
	}
	
	/**
	 * Fill Age Confirmation
	 * @return the Form POM
	 */
	public Form clickOnAge() {
		checkboxAge.click();
		return this;
	}
	
	/**
	 * Fill Card Number
	 * @return the Form POM
	 */
	public Form fillCC(String CC) {
		inputCC.click();
		inputCC.sendKeys(CC);
		return this;
	}
	
	/**
	 * Fill Expiration Date
	 * @return the Form POM
	 */
	public Form fillExpirationDate(String month, String year) {
		selectMonth(inputExpirationMonth).selectByValue(month); 
		selectYear(inputExpirationYear).selectByValue(year); 
		return this;
	}
	
	public Form fillFirstNameB(String firstname) {
		inputFirstNameB.click();
		inputFirstNameB.sendKeys(firstname);
		return this;
	}
	
	public Form fillLastNameB(String lastname) {
		inputLastNameB.click();
		inputLastNameB.sendKeys(lastname);
		return this;
	}
	
	public Form fillAddress1B(String address1) {
		inputAddress1B.click();
		inputAddress1B.sendKeys(address1);
		return this;
	}
	
	public Form fillAddress2B(String address2) {
		inputAddress2B.click();
		inputAddress2B.sendKeys(address2);
		return this;
	}
	
	public Form fillZipB(String zip) {
		inputZipB.click();
		inputZipB.sendKeys(zip);
		return this;
	}
	
	public Form fillStateB(String statecode) {
		String s;
		if (statecode.equalsIgnoreCase("") || statecode.equalsIgnoreCase(" ")){
			s = RandomUtil.generate_random_value(1, 63);
			if (Integer.valueOf(s) == 53){
				s = "54";
			}
			selectState(inputStateB).selectByIndex(Integer.valueOf(s)); 
		}else{
			s = statecode;
			selectState(inputStateB).selectByValue(s);
		}
		return this;
	}
	
	public Form fillCityB(String city) {
		inputCityB.click();
		inputCityB.sendKeys(city);
		return this;
	}
	
	public Form clickOnShipping() {
		checkBoxShippingB.click();
		return this;
	}
	
	public Form fillFirstNameS(String firstname) {
		inputFirstNameS.click();
		inputFirstNameS.sendKeys(firstname);
		return this;
	}
	
	public Form fillLastNameS(String lastname) {
		inputLastNameS.click();
		inputLastNameS.sendKeys(lastname);
		return this;
	}
	
	public Form fillAddress1S(String address1) {
		inputAddress1S.click();
		inputAddress1S.sendKeys(address1);
		return this;
	}
	
	public Form fillAddress2S(String address2) {
		inputAddress2S.click();
		inputAddress2S.sendKeys(address2);
		return this;
	}
	
	public Form fillZipS(String zip) {
		inputZipS.click();
		inputZipS.sendKeys(zip);
		return this;
	}
	
	public Form fillStateS(String statecode) {
		String s;
		if (statecode.equalsIgnoreCase("") || statecode.equalsIgnoreCase(" ")){
			s = RandomUtil.generate_random_value(1, 63);
			if (Integer.valueOf(s) == 53){
				s = "54";
			}
			selectState(inputStateS).selectByIndex(Integer.valueOf(s)); 
		}else{
			s = statecode;
			selectState(inputStateS).selectByValue(s);
		}
		return this;
	}
	
	public Form fillCityS(String city) {
		inputCityS.click();
		inputCityS.sendKeys(city);
		return this;
	}
	
	public Form clickOnTerms() {
		checkBoxTermsAndCondtions.click();
		return this;
	}
	
	/*public Complete completeOrderSuccessfull() {
		buttonCompleteOrder.click();
		return this;
	}*/
	
	public Form completeOrderFailed() {
		buttonCompleteOrder.click();
		return this;
	}
	
	/**
	 * Fill Account information Form
	 * @return the Form POM
	 */
	public Form fillForm(String email, String pwd, String pwdconfirm, String phone, Boolean age){
		if (!email.equalsIgnoreCase("")){
			fillEmail(email);
		}
		if (!pwd.equalsIgnoreCase("")){
			this.fillPassword(pwd);
		}
		if (!pwdconfirm.equalsIgnoreCase("")){
			this.fillPasswordConfirm(pwdconfirm);
		}
		if (!phone.equalsIgnoreCase("")){
			this.fillPhone(phone);
		}
		if (age){
			this.clickOnAge();
		}
		return this;
	}


	public Complete completeOrder() {
		buttonCompleteOrder.click();
		return new Complete(driver) ;
		
	}
	
	
}
