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
	
	/**
	 * Deletes a file or directory. Directory must be empty to be deleted.
	 * @param File
	 * @return success
	 */
	public boolean deleteFile(File file){
		boolean success = false;
		if (file.exists()){
			success = file.delete();
		}
		return success;
	}
	
	public boolean deleteFile(String path){
		return deleteFile(new File(path));
	}
	
	/**
	 * Copies a file from one
	 * @param file
	 * @param destination
	 */
	public void copyFile(File file, String destination){
		try 
		{
			File target = new File(destination);
			Files.copy(file.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void copyFile(String path, String destination){
		copyFile(new File(path), destination);
	}
	
	public boolean compareHashes(String hash1, String hash2){
		return (hash1 == hash2);
	}
}
