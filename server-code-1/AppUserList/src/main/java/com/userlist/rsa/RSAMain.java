package com.userlist.rsa;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSAMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String encryptedText = encryptText();
		System.out.println("Ecncrypted text = " + encryptedText);
		
		System.out.println("De-crypted text = " + decrypt(encryptedText) + "\n for encryptedText = "+encryptedText);

	}

	public static String encryptText() {
		String encryptedMessage = "";
		try {
			String secretMessage = "Some Message to Encrypt";

			Cipher encryptCipher = Cipher.getInstance("RSA");

			String publicKeyString = getFile("/public.key");

			publicKeyString = publicKeyString.replaceAll("-----BEGIN (.*)-----|-----END (.*)-----|\\s", "");

			byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(keyBytes);
			Key publicKey = keyFactory.generatePublic(publicKeySpec);

			encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

			byte[] secretMessageBytes = secretMessage.getBytes(StandardCharsets.UTF_8);
			byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);

			encryptedMessage = Base64.getEncoder().encodeToString(encryptedMessageBytes);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedMessage;

	}

	public static String decrypt(String encryptedMessage) {

		String result = "";

		try {

			Cipher decryptCipher = Cipher.getInstance("RSA");

			String privateKeyString = getFile("/private_key.pem");

			privateKeyString = privateKeyString.replaceAll("-----BEGIN (.*)-----|-----END (.*)-----|\\s", "");

			byte[] keyBytes = Base64.getDecoder().decode(privateKeyString);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			EncodedKeySpec publicKeySpec = new PKCS8EncodedKeySpec(keyBytes);
			Key privateKey = keyFactory.generatePrivate(publicKeySpec);

			decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);

			byte[] decryptedBytes = decryptCipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
			result = new String(decryptedBytes);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public static String getFile(String fileName) {
		String filePath = fileName;
		InputStream inputStreamFile;
		byte[] bytes;
		try {
			inputStreamFile = RSAMain.class.getResourceAsStream(filePath);
			bytes = new byte[inputStreamFile.available()];
			inputStreamFile.read(bytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new String(bytes);
	}
}
