package main.java;

/**
 * @author Jerry Binder
 * Uses Bill Pugh Singleton to provide access to a single instance of FileOperations.
 * Thread safe.
 */
public class FileOperations {
	private FileOperations instance;
	
	private static class SingletonHelper{
		private static final FileOperations INSTANCE = new FileOperations();
	}
	
	public static FileOperations getInstance(){
		return SingletonHelper.INSTANCE;
	}
	
	public void createFolder(String path){
		
	}
	
	public void deleteFile(String path){
		
	}
	
	public void copyFile(String path, String destination){
		
	}
	
	public boolean compareHashes(String hash1, String hash2){
		return (hash1 == hash2);
	}
}