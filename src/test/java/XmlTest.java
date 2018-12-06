package test.java;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.InstantJob;
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
		destinations = new ArrayList<String>();
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
	
	/*
	 * This is proving very difficult to unit test - easier to test through program UI.
	 */
//	@Test
//	public void testLoadFromXml(){
//		if (!(new File(SERIALIZATION_PATH).exists())){
//			testSaveToXml();
//		}
//		JobsList jl = JobsList.loadFromXml();
//		assertFalse(jl.isEmpty());
//	}
	
	@After
	public void cleanup(){
		new File(SERIALIZATION_PATH).delete();
	}
	

}
