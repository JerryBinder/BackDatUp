package main.java;

public class RecurringJob extends Job {
	int interval;		// number of sec/ms/whatever between each recurrance
	int timesToRepeat;	// number of times job will recur

	@Override
	public boolean performJob() {
		// TODO Auto-generated method stub
		// TODO make sure to advance Calendar by the interval, then decrement timesToRepeat
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
