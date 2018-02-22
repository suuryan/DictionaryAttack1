package com.trade.security;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PasswordHashingTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHashPassword() {
		PasswordHashing pwdHash = new PasswordHashing();
		String username = "johnny";
		String password = "password";
		String hashedPassword = "5f4dcc3b5aa765d61d8327deb882cf99";
		String unhasedPassword = "5f4dcc3b5aa765d61d8327deb882cf9923";
		String returnStr = pwdHash.hashPassword(username, password);
		assertNotNull(returnStr);		
		assertTrue(hashedPassword.equals(returnStr));
		assertFalse(unhasedPassword.equals(returnStr));
	}

	@Test
	public void testGenerateHash() {
		PasswordHashing pwdHash = new PasswordHashing();
		String password = "password";
		String hashedPassword = "5f4dcc3b5aa765d61d8327deb882cf99";
		String unhasedPassword = "5f4dcc3b5aa765d61d8327deb882cf9923";
		String returnStr = pwdHash.generateHash(password);
		assertNotNull(returnStr);		
		assertTrue(hashedPassword.equals(returnStr));
		assertFalse(unhasedPassword.equals(returnStr));
	}	

}
