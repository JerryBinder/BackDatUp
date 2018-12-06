package main.java;

import java.io.File;
import java.util.Calendar;

/**
 * @author Jerry Binder
 * Schedule containing all Jobs.
 */
public class Schedule {
	
	private JobsList jobs;
	
	private static class SingletonHelper{
		private static final Schedule INSTANCE = new Schedule();
	}
	
	public static Schedule getInstance(){
		return SingletonHelper.INSTANCE;
	}
	
	Schedule(){
		jobs = new JobsList(true);
		// change to false to disable XML loading
	}

	/**
	 * Iterates through list of jobs. Calls performJob() on all that are due.
	 */
	public void checkForDueJobs(){
		System.out.println("Checking for due jobs...");
		for(Job j : jobs){
			if(j.getTiming().before(Calendar.getInstance())){
				System.out.println("Performing job: " + j.toString());
				j.performJob();
				if(j.getTimesToRepeat() <= 0){
					jobs.remove(j);
					jobs.serializeInXml();
					System.out.println("Removing expired job: " + j.destinationPaths.get(0).toString() + "\n" + j.getTiming());
				}	
			}
		}
		jobs.serializeInXml();
		System.out.println("Done checking for due jobs.");
	}
	
	/**
	 * Deletes a job from the schedule.
	 * @param Job
	 * @return false if job doesn't exist, true if it was deleted
	 */
	public boolean deleteJob(Job job, boolean deleteBackupsToo){
		if(deleteBackupsToo)
			job.deleteBackups();
		return jobs.remove(job);
	}
	
	/**
	 * Deletes a job from the schedule.
	 * @param sourceFile
	 * @return false if job doesn't exist, true if it was deleted
	 */
	public boolean deleteJob(File destinationFile, boolean deleteBackupsToo){
		for(Job j : jobs){
			for(File path : j.getDestinationPaths())
				if(path == destinationFile){
					return deleteJob(j, deleteBackupsToo);
				}
		}
		return false;
	}
	
	/**
	 * Adds a job to the schedule.
	 * @param Job
	 */
	public boolean addJob(Job job){
		return jobs.add(job);
	}
	
	public JobsList getJobs(){
		return jobs;
	}
}
