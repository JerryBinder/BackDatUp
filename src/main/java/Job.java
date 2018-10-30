/**
 * 
 */
package main.java;

import java.util.Calendar;
import java.util.Set;

/**
 * @author Jerry Binder
 *
 */
public abstract class Job {
	BackupFile sourceFiles;
	Set<String> destinationPaths;
	Calendar timing;
	private FileOperations fileOperations;
	
	Job(BackupFile sourceFiles, Set<String> destinationPaths, Calendar timing){
		this.sourceFiles = sourceFiles;
		this.destinationPaths = destinationPaths;
		this.timing = timing;
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
