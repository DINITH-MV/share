package web.service;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Business logic to handle registration functions.
 * 
 * @author Ahsan.
 */
public class RegistrationService {

	public static boolean register(String fName, String lName, String email, String dob, String password, String confPassword) {
		if (fName == null || lName == null || email == null || dob == null) {
	        return false;
	    }
	    
	    // Check if any parameter is empty or just whitespace
	    if (fName.trim().isEmpty() || lName.trim().isEmpty() || 
	        email.trim().isEmpty() || dob.trim().isEmpty() || password.isEmpty() || confPassword.isEmpty()) {
	        return false;
	    }
	    
	    if (!password.equals(confPassword)) {
	    	return false;
	    }
	    
	    if (LocalDate.parse(dob).getYear() < 1900) {
	    	return false;
	    }
	    
	    final Pattern EmailCheck = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
	    
	    if (!EmailCheck.matcher(email).matches()) {
            return false;
        }
        
        System.out.println("First name: " + fName);
        System.out.println("Last name: " + lName);
        System.out.println("Email: " + email);
        System.out.println("DoB (yyyy-mm-dd): " + dob);
        System.out.println("Password: " + password);
        System.out.println("Confirm Password: " + confPassword);
        return true;
	}
}
