package main.java;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.File;

public class InstantJob extends Job {
	
	InstantJob(String sourceFile, ArrayList<String> destinationPaths, Calendar timing){
		super(sourceFile, destinationPaths, timing);
	}
	
	InstantJob(String sourceFile, ArrayList<String> destinationPaths){
		this(sourceFile, destinationPaths, Calendar.getInstance());
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
		return true;	// TODO make this actually return success/failure via verifyCompletion
	}
	
	private boolean verifyCompletion() {
		// TODO Auto-generated method stub
		return true;
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
