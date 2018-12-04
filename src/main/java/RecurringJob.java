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
	int interval;		// number of minutes between each recurrance
	int timesToRepeat;	// number of times job will recur
	
	RecurringJob(String sourceFile, ArrayList<String> destinationPaths, Calendar timing, int interval, int timesToRepeat){
		super(sourceFile, destinationPaths, timing);
		this.interval = interval;
		this.timesToRepeat = timesToRepeat;
	}
	
	RecurringJob(String sourceFile, ArrayList<String> destinationPaths, int interval, int timesToRepeat){
		this(sourceFile, destinationPaths, Calendar.getInstance(), interval, timesToRepeat);
	}

	RecurringJob(String sourceFile, ArrayList<String> destinationPaths, Calendar timing) {
		super(sourceFile, destinationPaths, timing);
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
		
		timesToRepeat--;
		if(timesToRepeat > 0){
			timing.add(Calendar.MINUTE, interval);	// TODO will this loop between days? must test
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
		timesToRepeat--;
	}


	@Override
	public void deleteJob() {
		// TODO Auto-generated method stub
		
	}
	
	@XmlElement
	public int getInterval() { return interval; }
	
	@XmlElement
	public int getTimesToRepeat() { return timesToRepeat; }

}
