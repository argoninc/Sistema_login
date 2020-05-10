package com.github.argoninc.criptografy;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cripto {

	public static Double generateHash() {
		return Math.random();
	}

	public static byte[] getMD5(String texto) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(texto.getBytes());
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

	public static String getPassCript(String pass, Double hash) {

		String hashText = hash.toString();

		return getMD5(pass + hashText).toString();
	}

}
