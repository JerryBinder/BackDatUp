package main.java;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.File;

/**
 * Job that happens only once.
 * @author Jerry Binder
 *
 */
public class InstantJob extends Job {
	
	// exists to make XML loader happy - don't call it
	InstantJob(){}
	
	public InstantJob(File sourceFile, ArrayList<File> destinationPaths, Calendar timing){
		super(sourceFile, destinationPaths, timing);
	}
	
	public InstantJob(File sourceFile, ArrayList<File> destinationPaths){
		this(sourceFile, destinationPaths, Calendar.getInstance());
	}

	@Override
	public boolean performJob() {
		File f;
		FileOperations fileOperations = FileOperations.getInstance();
		for(File path : destinationPaths)
		{
			fileOperations.copyFile(sourceFile, path);
		}
		return this.verifyCompletion();
	}
	
	private boolean verifyCompletion(){
		HashFileUtil hfu = new HashFileUtil();
		String sourceHash = hfu.generateMd5Hash(sourceFile);
		String destinationHash;
		// List failedDestinations = new List();	// contains paths of all destinations that don't have the current file
		
		for(File f : destinationPaths){
			destinationHash = hfu.generateMd5Hash(f);
			if(sourceHash != destinationHash){
				// failedDestinations.add(d);
				return false;
			}
		}
		return true;
	}

	@Override
	public void addDestination(File destination) {
		destinationPaths.add(destination);
	}

	@Override
	public void removeDestination(File destination) {
		destinationPaths.remove(destinationPaths.indexOf(destination));
	}


	@Override
	public void deleteBackups() {
		FileOperations f = FileOperations.getInstance();
		for(File d : destinationPaths){
			f.deleteFile(d);
		}
	}

}
