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
	Set<BackupFile> sourceFiles;
	Set<String> destinationPaths;
	Calendar timing;
	
	/*
	 * Copies all items in sourceFiles to all paths in destinationPaths.
	 * Then, verifies that copy is complete and returns boolean result.
	 */
	public abstract boolean performJob();
	
	public abstract void addDestination(String destination);
	public abstract void removeDestination(String destination);
	public abstract void addFile(String file);
	public abstract void removeFile(String file);
	
	/*
	 * Deletes job, empties both sets.
	 */
	public abstract void deleteJob();
}
