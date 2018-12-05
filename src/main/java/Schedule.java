package main.java;

import java.io.File;
import java.util.Calendar;
import java.util.List;

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
	}

	/**
	 * Iterates through list of jobs. Calls performJob() on all that are due.
	 */
	public void checkForDueJobs(){
		for(Job j : jobs){
			if(j.getTiming().before(Calendar.getInstance())){
				j.performJob();
			}
		}
	}
	
	/**
	 * Deletes a job from the schedule.
	 * @param Job
	 * @return false if job doesn't exist, true if it was deleted
	 */
	public boolean deleteJob(Job job, boolean deleteBackupsToo){
		if(deleteBackupsToo)
			job.deleteJobAndBackups();
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
	
	public boolean deleteJob(String sourceFile){
		return deleteJob(new File(sourceFile));
	}
	
	/**
	 * Adds a job to the schedule.
	 * @param Job
	 */
	public void addJob(Job job){
		jobs.add(job);
	}
	
	public List<Job> getJobs(){
		return jobs;
	}
}
