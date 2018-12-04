package test.java;

import java.io.File;
import java.util.ArrayList;

import main.java.InstantJob;
import main.java.Job;
import main.java.JobsList;

public class ModelTest {
	public static void main(String[] args) {
			ArrayList<String> destinations = new ArrayList<String>();
			File f = new File("./test1.txt");
			System.out.println("test file exists = " + f.exists());
			
			Job job1 = new InstantJob(new File("./test1.txt"), destinations);
			Job job2 = new InstantJob(new File("./test2.txt"), destinations);
			JobsList list = new JobsList();
			list.add(job1);
			list.add(job2);
			// Schedule.getInstance().addJob(job);
			// Need to properly delete jobs.xml, probably. Doesn't throw any kind of error when there's no existing one
			// but when it does exist there's an infinite error message
	}

}
