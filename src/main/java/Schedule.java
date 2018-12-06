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
		for(Job j : jobs){
			if(j.getTimesToRepeat() < 0)
				jobs.remove(j);
			if(j.getTiming().before(Calendar.getInstance())){
				System.out.println("Performing job: " + j.toString());
				j.performJob();
				if(j.getTimesToRepeat() == 0){
					jobs.remove(j);
					System.out.println("Removing expired job: " + j.toString());
				}
					
			}
		}
	}
	
	/**
	 * Deletes a job from the schedule.
	 * @param Job
	 * @return false if job doesn't exist, true if it was deleted
	 */
	public boolean deleteJob(Job job, boolean deleteBackupsToo){	// TODO have GUI ask if you wanna delete backups too with Yes/no/cancel options
		if(deleteBackupsToo)
			job.deleteBackups();
		return jobs.remove(job);
	}
	
	/**
	 * Deletes a job from the schedule.
	 * @param sourceFile
	 * @return false if job doesn't exist, true if it was deleted
	 */
	public boolean deleteJob(File sourceFile){
		for(Job j : jobs){
			if(j.getSourceFile() == sourceFile){
				jobs.remove(j);
				return true;
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
