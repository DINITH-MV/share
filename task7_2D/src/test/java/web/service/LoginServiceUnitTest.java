package web.service;

import org.junit.Assert;
import org.junit.Test;

public class LoginServiceUnitTest {
	
	// Unit test cases	  	
	@Test
	public void testLoginSuccess() {
		Assert.assertTrue(LoginService.login("ahsan", "ahsan_pass", "2020-04-25"));
	}
	
    @Test
    public void testLoginFailureWrongUsername() {
        Assert.assertFalse(LoginService.login("wrong_user", "ahsan_pass", "2020-04-25"));
    }

    @Test
    public void testLoginFailureWrongPassword() {
        Assert.assertFalse(LoginService.login("ahsan", "wrong_pass", "2020-04-25"));
    }

    @Test
    public void testLoginFailureWrongDob() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "1990-01-01"));
    }

    @Test
    public void testLoginNullUsername() {
        Assert.assertFalse(LoginService.login(null, "ahsan_pass", "2020-04-25"));
    }

    @Test
    public void testLoginNullPassword() {
        Assert.assertFalse(LoginService.login("ahsan", null, "2020-04-25"));
    }

    @Test
    public void testLoginNullDob() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", null));
    }

    @Test
    public void testLoginEmptyUsername() {
        Assert.assertFalse(LoginService.login("", "ahsan_pass", "2020-04-25"));
    }

    @Test
    public void testLoginEmptyPassword() {
        Assert.assertFalse(LoginService.login("ahsan", "", "2020-04-25"));
    }

    @Test
    public void testLoginEmptyDob() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", ""));
    }

}
