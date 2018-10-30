/**
 * 
 */
package main.java

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Jerry Binder, Dustin Jackson
 *
 */
public abstract class Job {
	String sourceFiles;
	ArrayList<String> destinationPaths;
	Calendar timing;
	FileOperations fileOperations;
	
	Job(String sourceFiles2, ArrayList<String> destinationPaths, Calendar timing, FileOperations fileoperations){
		this.sourceFiles = sourceFiles2;
		this.destinationPaths = destinationPaths;
		this.timing = timing;
		this.fileOperations = fileoperations;
	}
	
	/**
	 * Copies all items in sourceFiles to all paths in destinationPaths.
	 * @return success
	 */
	public abstract boolean performJob();
	
	public abstract void addDestination(String destination);
	public abstract void removeDestination(String destination);
	public abstract void addFile(String file);
	public abstract void removeFile(String file);
	
	/**
	 * Deletes job, empties both sets.
	 */
	public abstract void deleteJob();
}

