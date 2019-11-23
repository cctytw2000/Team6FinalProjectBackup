package com.eeit109team6.finalproject.javaUtils;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * --------------------------------------------------------
 * CipherUtils.java提供功能： 
 * 1 : 字串加密(對稱式加密)，encryptString(String key, String message, byte[] iv) 
 * 2 : 字串解密(對稱式解密)，decryptString(String key, String stringToDecrypt, byte[] iv)
 * key(金鑰): 		長度必須是16個位元組或24個位元組或32個位元組
 * message: 		要加密的字串
 * stringToDecrypt:	要解密的字串	 
 * 
 * 注意: 如果你的金鑰是24個位元組或32個位元組, 你必須要到下列網址宣誓你不是恐怖分子後，
 * 下載額外的壓縮檔，將其內的兩個jar檔複製到所使用之JVM的
 * ${java.home}/jre/lib/security/資料夾內，覆蓋掉原有的兩個jar檔(local_policy.jar與US_export_policy.jar)。
 * 
 * jdk6: http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html 
 * jdk7: http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html 
 * jdk8: http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
 * 
 * Author:
 * 
 * @author S.C.Wang, apriori302@gmail.com
 *
 */
public class CipherUtils {
//	private static String transformationECB = "AES/ECB/PKCS5Padding";
	private static String transformationCBC = "AES/CBC/PKCS5Padding";
	
	public static String encryptString(String key, String message, byte[] iv)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
		// 呼叫Cipher.getInstance("AES/CBC/PKCS5Padding")取得Cipher物件，
		// 無論是加密或解密都會用到Cipher類別。此類別是Java Cryptographic Extension (JCE)框架
		// 的核心類別，呼叫此方法時必須傳入一個代表加解密運算的字串(稱為transformation)。
		// 此字串的格式必須是 "algorithm/mode/padding" or "algorithm"
		// 說明: 
		// algorithm :	
		// AES: The standard comprises three block ciphers, AES-128, AES-192 and AES-256, 
		// adopted from a larger collection originally published as Rijndael. 
		// Each of these ciphers has a 128-bit block size, with key sizes of 128, 
		// 192 and 256 bits, respectively
		// mode		 : EBC	 
		Cipher cipher = Cipher.getInstance(transformationCBC);
		// padding: 
		// http://www.cnblogs.com/midea0978/articles/1437257.html
		//
        // mode: https://en.wikipedia.org/wiki/Block_cipher_mode_of_operation
		// 
		// javax.crypto.spec.SecretKeySpec類別會根據一個『代表密碼的位元組陣列』來
		// 建構一個金鑰(javax.crypto.SecretKey)，此位元組陣列是由密碼(以字串型式存在)
		// 的getBytes()得到，如果採用AES演算法，位元組陣列的長度必須是16個位元組、
		// 24個位元組或32個位元組。本方法採用『AES』對稱式加密演算法。
		//
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
		// Cipher物件初始化時應說明進行加密(Cipher.ENCRYPT_MODE)
		// 或解密(Cipher.DECRYPT_MODE)，同時傳入金鑰secretKey。
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
		// cipher.doFinal:開始加密，傳回值為位元組陣列
		byte[] ba = cipher.doFinal(message.getBytes());
		// 對加密後得到的位元組陣列進行Base64編碼。
		// 任何形式的資料以Base64的方式編碼後就會轉為由A-Z, a-z, 0-9 與 + /
		// 等64個字元所組成的字串。
//		String encryptedString = DatatypeConverter.printBase64Binary(ba);
		String encryptedString = Base64.getEncoder().encodeToString(ba);
		return encryptedString;
	}

	/**
	 * 本方法可對加密之字串(Ciphertext)解密，key為當初加密時的金鑰，傳回值為解密後的字串(Plaintext)
	 * 
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @param key
	 *            : 加密金鑰(即加密的密碼)，必須是16或24或32個位元組
	 * 
	 * @param stringToDecrypt
	 *            : 已加密的字串
	 * 
	 * @return 解密後的字串
	 * @throws InvalidAlgorithmParameterException 
	 */
	public static String decryptString(String key, String stringToDecrypt, byte[] iv)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {

		// 取得Cipher物件
		Cipher cipher = Cipher.getInstance(transformationCBC);

		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
		// Cipher物件初始化時應說明進行加密(Cipher.ENCRYPT_MODE)或解密(Cipher.DECRYPT_MODE)，
		// 同時要傳入金鑰
		cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

		// 先將加密的字串由BASE64的字串反轉為byte[]
//		byte[] b = DatatypeConverter.parseBase64Binary(stringToDecrypt);
		byte[] b = Base64.getDecoder().decode(stringToDecrypt);
		// 呼叫cipher.doFinal(b)開始對參數b進行解密
		String decryptedString = new String(cipher.doFinal(b));

		return decryptedString;
	}
	// 本方法可對加密之字串(Ciphertext)解密，key為當初加密時的金鑰，傳回值為解密後的字串(Plaintext)
	// 無IV版本
	public static String encryptString(String key, String message) 
    {
	
	String encryptedString = "";
	try {
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		//encryptedString = DatatypeConverter.printBase64Binary(cipher.doFinal(message.getBytes()));
		encryptedString = Base64.getEncoder().encodeToString(cipher.doFinal(message.getBytes()));
	} catch (InvalidKeyException e) {
		e.printStackTrace();
	} catch (NoSuchAlgorithmException e) {
		e.printStackTrace();
	} catch (NoSuchPaddingException e) {
		e.printStackTrace();
	} catch (IllegalBlockSizeException e) {
		e.printStackTrace();
	} catch (BadPaddingException e) {
		e.printStackTrace();
	}
	return encryptedString;
	}
	/** 
	 *   本方法可對加密之字串(Ciphertext)解密，key為當初加密時的金鑰
	 *   傳回值為解密後的字串(Plaintext)
	 *   無IV版本
	 */
	public static String decryptString(String key, String stringToDecrypt)
			 {
		String decryptedString = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
//			byte[] b = DatatypeConverter.parseBase64Binary(stringToDecrypt);
			byte[] b = Base64.getDecoder().decode(stringToDecrypt);
			decryptedString = new String(cipher.doFinal(b));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return decryptedString;
	}	
	
}
