package main.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * @author Jerry Binder, Dustin Jackson
 * Uses Bill Pugh Singleton to provide access to a single instance of FileOperations.
 * Thread safe.
 */
public class FileOperations {
	private static class SingletonHelper{
		private static final FileOperations INSTANCE = new FileOperations();
	}
	
	public static FileOperations getInstance(){
		return SingletonHelper.INSTANCE;
	}
	
	public boolean createFolder(String path){
		boolean success = (new File(path)).mkdir();
		return success;
	}
	
	public boolean deleteFile(String path){
		File temp = new File(path);
		boolean success = false;
		if (temp.exists()){
			success = (new File(path)).delete();
		}
		return success;
	}
	
	public void copyFile(String path, String destination){
		
		try 
		{
			File src = new File(path);
			File target = new File(destination);
			Files.copy(src.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean compareHashes(String hash1, String hash2){
		return (hash1 == hash2);
	}
}
