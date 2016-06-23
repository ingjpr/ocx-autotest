/**
 * This class is a set of functions to provide data input for tests
 * Author JPR
 * Date 31 May 2016
 */
package appModules;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomUtil {
	/**
	 * Generate a unique email based on Date
	 * @param domain : could be mailinator or yopmail
	 * @return email : String with unique email
	 */
	public static String generate_email(String domain){
		String email;
		DateFormat dateFormat = new SimpleDateFormat("MMddyyHms");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		email = "Rob"+dateFormat.format(date)+"@"+domain+".com";
	    return email;
	}
	
	/**
	 * Generate unique text based on date, to use in firstname and lastname
	 * @return String with letters A-J based on MM-dd-yy-H-m-s to make it unique
	 */
	public static String generate_unique_text(){
		String uniquetext = "";
		String str;
		char[] strarray;
		
		Random rndch = new Random();
		char c = (char)(rndch.nextInt(26) + 'a');
		
		DateFormat dateFormat = new SimpleDateFormat("MMddyyHms");
		Date date = new Date();
		str = dateFormat.format(date);
		strarray= str.toCharArray();
		for (int i= 0; i<strarray.length;i++){		   
			uniquetext = uniquetext+change_digit_to_alpha(Character.getNumericValue(strarray[i]));
		}
	    return c+uniquetext;
	}
	
	/**
	 * Generate random text based on random number, you could select the length of text using the parameter
	 * @param d : this parameter sets the length of string
	 * @return String with "d" letters A-J based on a random numeric value. 
	 */
	public static String generate_random_text(int d){
		String randomtext = "";
		String str;
		char[] strarray;
		str = generate_random_value(0,999999999);
		str = str.substring(0, d);
		strarray = str.toCharArray();
		for (int i= 0; i<strarray.length;i++){		   
			randomtext = randomtext+change_digit_to_alpha(Character.getNumericValue(strarray[i]));
		 }
	    return randomtext;
	}
	
	/**
	 * Generate a US fake address for testing purposes
	 * @param type : address1 or address2. Complete address when address1 and just a line when address2
	 * @return String with address1 or address2
	 */
	public static String generate_address(String type){
		String address = "";
		if(type.equalsIgnoreCase("address1") || type.equalsIgnoreCase("")){
			String number = RandomUtil.generate_random_value(0000,9999);
			String cardinals = pick_random_cardinals();
			String streetname = generate_random_text(5);
			String street = pick_random_street();
			address = number+" "+cardinals+" "+streetname+" "+street;
		}else{
			String streetname = generate_random_text(5);
			String street = pick_random_street();
			address = streetname+" "+street;
		}
		return address;
	}
	
	/**
	 * Replace the integer by a letter, created to generate text for testing purposes. 0-9to A-J.
	 * @param i : input integer 0-9, other generate a random between A-J
	 * @return String with value A-J corresponding to digit 0-9
	 */
	public static String change_digit_to_alpha(int i){
		String ch;
		switch(i){
			case 0: ch = "A";
			break;
			case 1: ch = "B";
			break;
			case 2: ch = "C";
			break;
			case 3: ch = "D";
			break;
			case 4: ch = "E";
			break;
			case 5: ch = "F";
			break;
			case 6: ch = "G";
			break;
			case 7: ch = "H";
			break;
			case 8: ch = "I";
			break;
			case 9: ch = "J";
			break;
			default:
				Random rndvalue = new Random();
				char k = (char) (rndvalue.nextInt((9 - 0) + 1) + 0);
				ch = change_digit_to_alpha(k);
				break;
		}
		return ch;
	}
	
	/**
	 * Generate a numeric random value in a STRING given a range
	 * @param min : min range
	 * @param max : max range
	 * @return String with random number
	 */
	public static String generate_random_value(int min, int max){
		String value;
		Random rndvalue = new Random();
		int k = rndvalue.nextInt((max - min) + 1) + min;
		value = String.valueOf(k);
		return value;
	}
	
	/**
	 * Select a Random cardinal value for create the address
	 * @return String with a cardinal point abbreviation
	 */
	public static String pick_random_cardinals(){
		String[] cardinals = {"N", "S", "E", "W", "NE", "SE", "NW", "SW"};
		int i = Integer.valueOf(generate_random_value(0,7));
		return cardinals[i];
	}
	
	/**
	 * Select a Random street value for create the address
	 * @return String with a street abbreviation
	 */
	public static String pick_random_street(){
		String[] street = {"St", "Ave", "Dr", "Blvd", "Rd", "SQ", "Pt"};
		int i = Integer.valueOf(generate_random_value(0,6));
		return street[i];
	}
	
	/**
	 * Select a Random ZIP value and put in an ARRAY. Key is the zip, Value is the state
	 * @return Array with zip code in [0] and state name in [1]
	 */
	public static String[] pick_random_zip(){
		Map<String, String> zip_state = new HashMap<String, String>();
		zip_state.put("35811", "Alabama");
		zip_state.put("10007", "New York");
		zip_state.put("94102", "California");
		zip_state.put("20022", "District of Columbia");
		zip_state.put("33178", "Florida");
		zip_state.put("53969", "Wisconsin");
		zip_state.put("82714", "Wyoming");
		
		Random       random    = new Random();
		List<String> keys      = new ArrayList<String>(zip_state.keySet());
		String       randomKey = keys.get( random.nextInt(keys.size()) );
		String       value     = zip_state.get(randomKey);
		String[] z_s = {randomKey,value}; 
		return  z_s;
	}
	
	/**
	 * Select a Random CC value and put in an ARRAY. Key is the CC type, Value is the number
	 * @return Array with cc name in [0] and number in [1]
	 */
	public static String[] pick_random_cc(){
		Map<String, String> cc_number = new HashMap<String, String>();
		cc_number.put("MasterCard", "5555555555554444");
		cc_number.put("Visa", "4111111111111111");
		cc_number.put("AmericanExpress", "371449635398431");
				
		Random       random    = new Random();
		List<String> keys      = new ArrayList<String>(cc_number.keySet());
		String       randomKey = keys.get( random.nextInt(keys.size()) );
		String       value     = cc_number.get(randomKey);
		String[] c_n = {randomKey,value}; 
		return  c_n;
	}
	
	/**
	 * Generate Random FUTURE expiration date returning in a string [MM, YYYY]
	 * @return Array with mm in [0] and yyyy in [1]
	 */
	public static String[] generate_expiration_date(){
		DateFormat dateFormat = new SimpleDateFormat("MM-YY");
	    Date date = new Date();
		String str = dateFormat.format(date);
		String[] strvalues = str.split("-");
		int minm = Integer.valueOf(strvalues[0]);
		int miny = Integer.valueOf(strvalues[1]);
		
		String m = RandomUtil.generate_random_value(minm, 12);
		if (m.length() < 2){
			m = "0"+m;
		}
		String y = RandomUtil.generate_random_value(miny, 35);
		y = "20"+y;
		String[] exp = {m,y};
		return exp;
	}
	
	/**
	 * Select a Random promocode and put in an ARRAY. Key is the type and value is the promocode
	 * @return Array with promocode in [0] and discount in [1]
	 */
	public static String[] pick_promocode(){
		Map<String, String> product_code = new HashMap<String, String>();
		product_code.put("SUMMER50", "50");
		product_code.put("SUMMER20", "20");
		product_code.put("SUMMER10", "10");
		product_code.put("SUMMERKMONEY", "5");
		product_code.put("BCSUMMER", "Free shipping");
				
		Random			random    = new Random();
		List<String> 	keys      = new ArrayList<String>(product_code.keySet());
		String       	randomKey = keys.get( random.nextInt(keys.size()) );
		String      	value     = product_code.get(randomKey);
		String[] p_c = {randomKey,value}; 
		return  p_c;
	}
}

/*
<option value="AL">Alabama</option>
<option value="AK">Alaska</option>
<option value="AZ">Arizona</option>
<option value="AR">Arkansas</option>
<option value="CA">California</option>
<option value="CO">Colorado</option>
<option value="CT">Connecticut</option>
<option value="DE">Delaware</option>
<option value="DC">District of Columbia</option>
<option value="FL">Florida</option>
<option value="GA">Georgia</option>
<option value="HI">Hawaii</option>
<option value="ID">Idaho</option>
<option value="IL">Illinois</option>
<option value="IN">Indiana</option>
<option value="IA">Iowa</option>
<option value="KS">Kansas</option>
<option value="KY">Kentucky</option>
<option value="LA">Louisiana</option>
<option value="ME">Maine</option>
<option value="MD">Maryland</option>
<option value="MA">Massachusetts</option>
<option value="MI">Michigan</option>
<option value="MN">Minnesota</option>
<option value="MS">Mississippi</option>
<option value="MO">Missouri</option>
<option value="MT">Montana</option>
<option value="NE">Nebraska</option>
<option value="NV">Nevada</option>
<option value="NH">New Hampshire</option>
<option value="NJ">New Jersey</option>
<option value="NM">New Mexico</option>
<option value="NY">New York</option>
<option value="NC">North Carolina</option>
<option value="ND">North Dakota</option>
<option value="OH">Ohio</option>
<option value="OK">Oklahoma</option>
<option value="OR">Oregon</option>
<option value="PA">Pennsylvania</option>
<option value="RI">Rhode Island</option>
<option value="SC">South Carolina</option>
<option value="SD">South Dakota</option>
<option value="TN">Tennessee</option>
<option value="TX">Texas</option>
<option value="UT">Utah</option>
<option value="VT">Vermont</option>
<option value="VA">Virginia</option>
<option value="WA">Washington</option>
<option value="WV">West Virginia</option>
<option value="WI">Wisconsin</option>
<option value="WY">Wyoming</option>
<option value=" ">--</option>
<option value="AA">Armed Forces (Americas)</option>
<option value="AE">Armed Forces (Europe, Canada, Middle East, Africa)</option>
<option value="AP">Armed Forces (Pacific)</option>
<option value="AS">American Samoa</option>
<option value="FM">Federated States of Micronesia</option>
<option value="GU">Guam</option>
<option value="MH">Marshall Islands</option>
<option value="MP">Northern Mariana Islands</option>
<option value="PW">Palau</option>
<option value="PR">Puerto Rico</option>
<option value="VI">Virgin Islands</option>
 */



