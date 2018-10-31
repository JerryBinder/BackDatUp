package main.java;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.File;

public class RecurringJob extends Job {
	int interval;		// number of sec/ms/whatever between each recurrance
	int timesToRepeat;	// number of times job will recur
	
	RecurringJob(String sourceFiles, ArrayList<String> destinationPaths, Calendar timing, int interval, int timesToRepeat, FileOperations fileoperations){
		super(sourceFiles, destinationPaths, timing, fileoperations);
		this.interval = interval;
		this.timesToRepeat = timesToRepeat;
		this.fileOperations = fileoperations;
	}

	@Override
	public boolean performJob() {
		File f;
		f = new File(destinationPaths.get(i));
		for(int i = 0; i < this.destinationPaths.size(); i++)
		{
			f = new File(destinationPaths.get(i));
			if(f.exists() == false)
			{
				fileOperations.createFolder(destinationPaths.get(i));
			}
			fileOperations.copyFile(sourceFiles, destinationPaths.get(i));
		}
		timesToRepeat--;
		return true;
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
