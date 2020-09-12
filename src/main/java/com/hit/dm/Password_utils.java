package com.hit.dm;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
 
public class Password_utils { //Encryption methods to increase SECURITY & REGULAROTY Conditions
    
    private final SecureRandom RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int ITERATIONS = 65536;
    private final int KEY_LENGTH = 128;
    private static Password_utils instance;
    
    private Password_utils() {
    	
    }
    
    public static Password_utils getInstance() {
    	if(instance == null) {
    		instance = new Password_utils();
    	}
    	return instance;
    }
    
     public String getSalt(int length) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }
   
    private byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return fac.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }
    
    public String generateSecurePassword(String password, String salt) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        returnValue = Base64.getEncoder().encodeToString(securePassword);
        return returnValue;
    }
    
    public boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
        boolean returnValue = false;
        // Generate New secure password with the same salt
        String newSecurePassword = generateSecurePassword(providedPassword, salt);
        // Check if two passwords are equal
        //returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
        returnValue = newSecurePassword.equals(securedPassword);
        return returnValue;
    }
    
    public boolean validPassword(String password) {
    	boolean flag = true;
    	Matcher matcher;
    	Pattern specailCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        
    	if(password.length() < 10) {
    		flag = false;
    	}
    	
    	if(!specailCharPatten.matcher(password).find()) {
    		flag=false;
    	}
    	if(!UpperCasePatten.matcher(password).find()) {
    		flag=false;
    	}
    	if(!lowerCasePatten.matcher(password).find()) {
    		flag=false;
    	}
    	if(!digitCasePatten.matcher(password).find()) {	
    		flag=false;
    	}   	
    	return flag;
    }
}