package com.home.java.utils.crypt;

	import java.security.InvalidKeyException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
	
	public class CryptUtil {
		
     private static String algorithm = "DESede";
	 private static Key key = null;
	 private static Cipher cipher = null;
	 private static CryptUtil obj = new CryptUtil();
	 
	 private CryptUtil() {
	        try {
	            key = KeyGenerator.getInstance(algorithm).generateKey();
	         cipher = Cipher.getInstance(algorithm);
	        }

			 catch (Exception e) {
			 }

	    }

	public static CryptUtil getInstance() {
	        return obj;
	    }

	public static byte[] encrypt(String input) throws InvalidKeyException,BadPaddingException,IllegalBlockSizeException {
	        cipher.init(Cipher.ENCRYPT_MODE, key);
	     byte[] inputBytes = input.getBytes();
	     return cipher.doFinal(inputBytes);
	    }

	public static String getEncryptStringValue(String input) throws InvalidKeyException,BadPaddingException,IllegalBlockSizeException {
	        return new String(encrypt(input));
	    }

	 public static String decrypt(byte[] encryptionBytes) throws InvalidKeyException,BadPaddingException,IllegalBlockSizeException {
	     cipher.init(Cipher.DECRYPT_MODE, key);
	     byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
	     String recovered = new String(recoveredBytes);
	     return recovered;
	 }
	 
	 public static void main (String[] args){
		 try{
		 System.out.println(CryptUtil.getInstance().getEncryptStringValue("sabeer"));
		 System.out.println(CryptUtil.getInstance().decrypt(CryptUtil.getInstance().getEncryptStringValue("sabeer").getBytes()));
		 }catch (Exception e){
			 
		 }
		 
		 //System.out.println(Crypt.getInstance().de);
	 }

}

