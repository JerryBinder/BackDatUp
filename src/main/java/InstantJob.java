package main.java;

import java.util.ArrayList;
import java.util.Calendar;

public class InstantJob extends Job {
	
	InstantJob(String sourceFiles, ArrayList<String> destinationPaths, Calendar timing, FileOperations fileoperations){
		super(sourceFiles, destinationPaths, timing, fileoperations);
	}

	@Override
	public boolean performJob() {
		for(int i = 0; i < this.destinationPaths.size(); i++)
		{
			fileOperations.copyFile(sourceFiles, destinationPaths.get(i));
		}
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
		
	}


	@Override
	public void deleteJob() {
		// TODO Auto-generated method stub
		
	}

}
