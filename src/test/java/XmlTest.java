package test.java;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import main.java.InstantJob;
import main.java.Job;
import main.java.JobsList;
import main.java.RecurringJob;


public class XmlTest {
	protected final String TEST_FILE_1 = "./test1.txt";
	protected final String TEST_FILE_2 = "./test2.txt";
	protected final String DEST_1 = "./1.txt";
	protected final String DEST_2 = "./2.txt";
	protected final String DEST_3 = "./3.txt";
	protected final String DEST_4 = "./4.txt";
	protected final static String SERIALIZATION_PATH = "./jobs.xml";
	
	protected ArrayList<String> destinations;
	protected JobsList jobs;
	protected Calendar timing;
	
	
	/**
	 * Creates a JobList containing an InstantJob and a RecurringJob.
	 */
	@Before
	public void setup(){
		jobs = new JobsList();
		timing = Calendar.getInstance();
	}
	
	@Test
	public void testSaveToXml(){
		destinations.add(DEST_1);
		destinations.add(DEST_2);
		
		timing.add(Calendar.SECOND, 30);
		jobs.add(new InstantJob(new File(TEST_FILE_1), destinations, timing));
		
		destinations.clear();
		destinations.add(DEST_3);
		destinations.add(DEST_4);
		
		timing.add(Calendar.SECOND, 30);
		jobs.add(new RecurringJob(new File(TEST_FILE_2), destinations, timing, 1, 5));
	}
	
	@Test
	public void testLoadFromXml(){
//		if (!(new File(SERIALIZATION_PATH).exists())){
//			testSaveToXml();
//		}
		boolean a = false, b = false, c = false, d = false;
		JobsList jl = JobsList.loadFromXml();
		for(Job j : jl){
			if(j.getDestinationPaths().contains(DEST_1))
				a = true;
			if(j.getDestinationPaths().contains(DEST_2))
				b = true;
			if(j.getDestinationPaths().contains(DEST_3))
				c = true;
			if(j.getDestinationPaths().contains(DEST_4))
				d = true;
		}
		assertTrue(a && b && c && d);
	}
}
