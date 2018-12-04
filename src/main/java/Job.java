package main.java;

import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Jerry Binder, Dustin Jackson
 *
 */
public abstract class Job {
	String sourceFile;
	ArrayList<String> destinationPaths;
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
	
	@XmlElement
	public String getSourceFile(){ return sourceFile; };
	
	@XmlElement
	public ArrayList<String> getDestinationPaths(){ return destinationPaths; };
	
	@XmlElement
	public Calendar getTiming(){ return timing; };
	
	/**
	 * Deletes job, empties both sets.
	 */
	public abstract void deleteJob();
}

