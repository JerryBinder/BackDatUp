/**
 * 
 */
package main.java;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Jerry Binder, Dustin Jackson
 *
 */
public abstract class Job {
	String sourceFile;
	ArrayList<String> destinationPaths;
	FileOperations fileOperations;
	Calendar timing;
	
	Job(String sourceFile, ArrayList<String> destinationPaths, Calendar timing){
		this.sourceFile = sourceFile;
		this.destinationPaths = destinationPaths;
	}
	
	/**
	 * Copies all items in sourceFiles to all paths in destinationPaths.
	 * @return success
	 */
	public abstract boolean performJob();
	
	public abstract void addDestination(String destination);
	public abstract void removeDestination(String destination);
	
	/**
	 * Deletes job, empties both sets.
	 */
	public abstract void deleteJob();
}

