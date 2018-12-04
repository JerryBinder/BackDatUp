package main.java;

import java.util.ArrayList;
import java.util.Calendar;
import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Job that happens only once.
 * @author Jerry Binder
 *
 */
public class InstantJob extends Job {
	
	public InstantJob(File sourceFile, ArrayList<String> destinationPaths, Calendar timing){
		super(sourceFile, destinationPaths, timing);
	}
	
	public InstantJob(File sourceFile, ArrayList<String> destinationPaths){
		this(sourceFile, destinationPaths, Calendar.getInstance());
	}

	@Override
	public boolean performJob() {
		File f;
		FileOperations fileOperations = FileOperations.getInstance();
		for(String path : destinationPaths)
		{
			f = new File(path);
			if(f.exists() == false)
			{
				fileOperations.createFolder(path);
			}
			fileOperations.copyFile(sourceFile, path);
		}
		return this.verifyCompletion();
	}
	
	private boolean verifyCompletion(){
		HashFileUtil hfu = new HashFileUtil();
		String sourceHash = hfu.generateMd5Hash(sourceFile);
		String destinationHash;
		// List failedDestinations = new List();	// contains paths of all destinations that don't have the current file
		
		for(String d : destinationPaths){
			destinationHash = hfu.generateMd5Hash(d);
			if(sourceHash != destinationHash){
				// failedDestinations.add(d);
				return false;
			}
		}
		return true;
	}

	@Override
	public void addDestination(String destination) {
		this.destinationPaths.add(destination);
	}

	@Override
	public void removeDestination(String destination) {
		this.destinationPaths.remove(destinationPaths.indexOf(destination));
	}


	@Override
	public void deleteJob() {
		// TODO Auto-generated method stub
		
	}

}
