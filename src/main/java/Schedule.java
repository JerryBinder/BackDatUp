package main.java;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

/**
 * @author jarre
 * Schedule containing all Jobs.
 */
public class Schedule implements Serializable {
	private static final long serialVersionUID = -6999613519117625792L;
	private List<Job> jobs;
	
	private static class SingletonHelper{
		private static final Schedule INSTANCE = new Schedule();
	}
	
	public static Schedule getInstance(){
		return SingletonHelper.INSTANCE;
	}
	
	/**
	 * Iterates through list of jobs. Calls performJob() on all that are due.
	 */
	public void checkForDueJobs(){
		for(Job j : jobs){
			if(j.timing.before(Calendar.getInstance())){
				j.performJob();
			}
		}
	}
	
	/**
	 * Deletes a job from the schedule.
	 * @param Job
	 * @return false if job doesn't exist, true if it was deleted
	 */
	public boolean deleteJob(Job job){
		for(Job j : jobs){
			if(job.equals(j)){
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
	public void addJob(Job job){
		jobs.add(job);
	}
}
