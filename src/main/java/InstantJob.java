package main.java;

import java.util.Calendar;
import java.util.Set;

public class InstantJob extends Job {
	
	InstantJob(Set<BackupFile> sourceFiles, Set<String> destinationPaths, Calendar timing){
		super(sourceFiles, destinationPaths, timing);
	}

	@Override
	public boolean performJob() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean verifyCompletion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addDestination(String destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeDestination(String destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addFile(String file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFile(String file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteJob() {
		// TODO Auto-generated method stub
		
	}

}
