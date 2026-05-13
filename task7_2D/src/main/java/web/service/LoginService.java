package web.service;

/**
 * Business logic to handle login functions.
 * 
 * @author Ahsan.
 */
public class LoginService {

	/**
	 * Static method returns true for successful login, false otherwise.
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean login(String username, String password, String dob) {
		
		if (username == null || password == null || dob == null) {
	        return false;
	    }
		
		if (username.isEmpty() || password.isEmpty() || dob.isEmpty()) {
            return false;
        }

		// Match a fixed user name and password.
		//
		if ("ahsan".equals(username) && "ahsan_pass".equals(password) && "2020-04-25".equals(dob)) {
			return true;
		}
		return false;
	}
	
	
}
