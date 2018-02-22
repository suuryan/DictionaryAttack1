package com.trade.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author surya nampelly
 *
 */

public class PasswordHashing {

	Map<String, String> credentials = new HashMap<String, String>();
	public static final String SALT = "$@lT";
	public static String hash1 = "7c6a180b36896a0a8c02787eeafb0e4c";

	public static void main(String args[]) {
		PasswordHashing pwdHashing = new PasswordHashing();
		pwdHashing.hashPasswordWithSalt("surya", "password1234");
		pwdHashing.hashPassword("surya", "password1");
		pwdHashing.hackPassword(hash1);

		// login should succeed.
		if (pwdHashing.login("surya", "password1234"))
			System.out.println("user login successfull.");

		// login should fail because of wrong password.
		if (pwdHashing.login("surya", "password"))
			System.out.println("User login successfull.");
		else
			System.out.println("user login failed.");
	}
	
	/**
	 * This method is used to hash password with Salt. It's works as counter measure for dictionary attack.
	 *
	 */
	public void hashPasswordWithSalt(String username, String password) {
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);
		credentials.put(username, hashedPassword);
		System.out.println("HashWithSalt: "+hashedPassword);
	}
	
	/**
	 * This method is used to hash password with out Salt. It's works as counter measure for dictionary attack.
	 *
	 */
	public String hashPassword(String username, String password) {
		String hashedPassword = generateHash(password);
		credentials.put(username, hashedPassword);
		System.out.println("HASH##: "+hashedPassword);
		return hashedPassword;
	}
	
	/**
	 * This method is used to login. 
	 *
	 */

	public Boolean login(String username, String password) {
		Boolean isAuthenticated = false;

		// remember to use the same SALT value use used while storing password
		// for the first time.
		String saltedPassword = SALT + password;
		String hashedPassword = generateHash(saltedPassword);

		String storedPasswordHash = credentials.get(username);
		if(hashedPassword.equals(storedPasswordHash)){
			isAuthenticated = true;
		}else{
			isAuthenticated = false;
		}
		return isAuthenticated;
	}
	
	/**
	 * This method is used to generate Hash.
	 *
	 */

	public String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("MD5");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}
	
	/**
	 * This method is used to compare the hash values from dicitonary list and obtained hashed passwords from a compromised database. It can also use to compare list of dictionary
	 * and list of passwords from database.
	 *
	 */
	public void hackPassword(String hash1) {		
		
		String username = "surya";
		String password = "password";
		
		String enterpriseHash = this.hashPassword(username, password);		
		
		if (enterpriseHash.equals(hash1)) {			
			System.out.println("The password is password");			
		} else {
			System.out.println("Tough password to crack");
		}		
		
	}
	
	
	


}
