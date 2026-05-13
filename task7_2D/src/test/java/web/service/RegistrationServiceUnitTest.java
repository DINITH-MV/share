package web.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class RegistrationServiceUnitTest {
	
	// Unit Tests 
	@Test
    public void testSuccessRegistration_resultTrue() {
        assertTrue(RegistrationService.register(
            "Dinith", "Vanderlan", "Dinith@example.com", "2001-09-20", "DinithPass1!", "DinithPass1!"
        ));
    }
	

    // check empty values
    @Test
    public void testEmptyFirstName_resultFalse() {
        assertFalse(RegistrationService.register(
            "", "Vanderlan", "Dinith@example.com", "2001-09-20", "DinithPass1!", "DinithPass1!"
        ));
    }

    @Test
    public void testEmptyLastName_resultFalse() {
        assertFalse(RegistrationService.register(
            "Dinith", "", "Dinith@example.com", "2001-09-20", "DinithPass1!", "DinithPass1!"
        ));
    }

    @Test
    public void testEmptyEmail_resultFalse() {
        assertFalse(RegistrationService.register(
            "Dinith", "Vanderlan", "", "2001-09-20", "DinithPass1!", "DinithPass1!"
        ));
    }

    @Test
    public void testEmptyDob_resultFalse() {
        assertFalse(RegistrationService.register(
            "Dinith", "Vanderlan", "Dinith@example.com", "", "DinithPass1!", "DinithPass1!"
        ));
    }

    @Test
    public void testEmptyPassword_resultFalse() {
        assertFalse(RegistrationService.register(
            "Dinith", "Vanderlan", "Dinith@example.com", "2001-09-20", "", "DinithPass1!"
        ));
    }

    @Test
    public void testEmptyConfirmPassword_resultFalse() {
        assertFalse(RegistrationService.register(
            "Dinith", "Vanderlan", "Dinith@example.com", "2001-09-20", "DinithPass1!", ""
        ));
    }

    // check password and confirm password
    @Test
    public void testPasswordsDoNotMatch_resultFalse() {
        boolean result = RegistrationService.register(
            "Dinith", "Vanderlan", "Dinith@example.com", "2001-09-20", "DinithPass1", "DinithWrong1!!"
        );
        assertFalse("Expected false when password and confirmPassword differ", result);
    }
    
    // check dob after 1900
    @Test
    public void testDobYearBefore1900_resultFalse() {
        assertFalse(RegistrationService.register(
            "Dinith", "Vanderlan", "Dinith@example.com", "1890-02-01", "DinithPass1!", "DinithPass1!"
        ));
    }
    
    @Test
    public void testValidDOBAfter1900_resultTrue() {
        boolean result = RegistrationService.register(
            "Dinith", "Vanderlan", "Dinith@gmail.com", "2001-09-20", "DinithPass1!", "DinithPass1!"
        );
        assertTrue("Expected true for all valid inputs born after 1900", result);
    }
    
    // invalid email check
    @Test
    public void testInvalidEmail_resultFalse() {
        boolean result = RegistrationService.register(
            "Dinith", "Vanderlan", "Dinithgmailcom", "2001-09-20", "DinithPass1!", "DinithPass1!"
        );
        assertFalse("Expected true for all valid inputs born after 1900", result);
    }
    
}
