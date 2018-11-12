package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jerry Binder
 * Utility to generate hash for a file.
 * Uses Bill Pugh Singleton to provide access to a single instance of HashFileUtil.
 * Thread safe.
 */
public class HashFileUtil {
	// Declared as constants up here to make changing them more convenient.
	private final static int BYTE_ARRAY_SIZE = 1024;
	private final static String HASH_ALGORITHM = "MD5";
	
	/**
	 * @param filename The fully qualified path of the file.
	 * @return md5Hash String containing MD5 hash.
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 */
	public String generateMd5Hash(String path){
		return generateMd5Hash(new File(path));
	}
	
	/**
	 * @param File
	 * @return md5Hash String containing MD5 hash.
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 */
	public String generateMd5Hash(File file){
		// ends if file does not exist or cannot be accessed
		if(!file.exists() || !file.canRead())
			return "";		
		
		// declarations
		MessageDigest md;
		FileInputStream fis;
		StringBuilder result = new StringBuilder();
		byte[] buffer = new byte[BYTE_ARRAY_SIZE];
		byte[] digest;
		int count;
		
		try{
			md = MessageDigest.getInstance(HASH_ALGORITHM);
			fis = new FileInputStream(file);
			
			// digests a set number of bytes from the file and puts the result in md.digest()
			while((count = fis.read(buffer)) != -1){
				md.update(buffer, 0, count);
			}
			fis.close();
			digest = md.digest();
		}catch(NoSuchAlgorithmException | IOException e){
			return "Failed to generate hash.";
		}
		
		// builds a hash string from the MD5 digest
		for(byte b : digest){
			result.append(String.format("%02x", b));
		}
		
		return result.toString();
	}
}
