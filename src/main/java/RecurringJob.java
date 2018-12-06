package main.java;

import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;

import java.io.File;

/**
 * Job that happens a set number of times.
 * @author Jerry Binder
 *
 */
public class RecurringJob extends Job {
	private int interval;		// number of minutes between each recurrance
	private int timesToRepeat;	// number of times job will recur
	
	// exists to make XML loader happy - don't call it
	RecurringJob(){}
	
	public RecurringJob(File sourceFile, ArrayList<File> destinationPaths, Calendar timing, int interval, int timesToRepeat){
		super(sourceFile, destinationPaths, timing);
		this.interval = interval;
		this.timesToRepeat = timesToRepeat;
	}
	
	public RecurringJob(File sourceFile, ArrayList<File> destinationPaths, int interval, int timesToRepeat){
		this(sourceFile, destinationPaths, Calendar.getInstance(), interval, timesToRepeat);
	}

	@Override
	public boolean performJob() {
		FileOperations fileOperations = FileOperations.getInstance();
		for(File path : destinationPaths)
		{
			fileOperations.copyFile(sourceFile, path);
		}
		
		// decrements timesToRepeat. If it's still > 0, it'll reset the timing as well.
		timesToRepeat--;
		if(timesToRepeat > 0){
			timing = Calendar.getInstance();
			timing.add(Calendar.MINUTE, interval);
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
				// failedDestinations.add(f);
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
	
	@XmlElement
	public int getInterval() { return interval; }
	
	@XmlElement
	@Override
	public int getTimesToRepeat() { return timesToRepeat; }

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public void setTimesToRepeat(int timesToRepeat) {
		this.timesToRepeat = timesToRepeat;
	}
}
