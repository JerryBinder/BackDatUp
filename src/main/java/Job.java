package main.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Jerry Binder, Dustin Jackson
 *
 */
public abstract class Job {
	protected File sourceFile;
	protected ArrayList<File> destinationPaths;
	protected Calendar timing;
	
	// exists to make XML loader happy - don't call it
	Job(){}
	
	Job(File sourceFile, ArrayList<File> destinationPaths, Calendar timing){
		this.sourceFile = sourceFile;
		this.destinationPaths = destinationPaths;
		this.timing = timing;
	}
	
	Job(String sourceFile, ArrayList<File> destinationPaths, Calendar timing){
		this(new File(sourceFile), destinationPaths, timing);
	}
	
	/**
	 * Copies all items in sourceFiles to all paths in destinationPaths.
	 * @return success
	 */
	public abstract boolean performJob();
	
	public abstract void addDestination(File destination);
	public abstract void removeDestination(File destination);
	
	@XmlElement
	public File getSourceFile(){ return sourceFile; };
	
	@XmlElement
	public ArrayList<File> getDestinationPaths(){ return destinationPaths; };
	
	@XmlElement
	public Calendar getTiming(){ return timing; };
	
	
	public void setSourceFile(File sourceFile) {
		this.sourceFile = sourceFile;
	}

	public void setDestinationPaths(ArrayList<File> destinationPaths) {
		this.destinationPaths = destinationPaths;
	}

	public void setTiming(Calendar timing) {
		this.timing = timing;
	}

	public abstract void deleteBackups();

	public int getTimesToRepeat() { return 0; }
	public int getInterval() { return 0; }
}

