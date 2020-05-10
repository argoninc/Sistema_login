package com.github.argoninc.criptografy;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cripto {

	public static Double generateHash() {
		return Math.random();
	}

	private static String getMD5(String texto) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
		     byte[] messageDigest = md.digest(texto.getBytes());
		     BigInteger number = new BigInteger(1, messageDigest);
		     String hashtext = number.toString(16);
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
     
	public static String getPassCript(String pass, Double hash) {

		String hashText = hash.toString();

		return getMD5(pass + hashText).toString();
	}

}
