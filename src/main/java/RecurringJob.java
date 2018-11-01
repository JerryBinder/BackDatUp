package main.java;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.File;

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
		
		return true;	// TODO make this actually return success/failure via verifyCompletion
	}
	
	private boolean verifyCompletion() {
		// TODO Auto-generated method stub
		return false;
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

}
