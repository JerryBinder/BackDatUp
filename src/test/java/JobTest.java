package test.java;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.java.InstantJob;
import main.java.Job;
import main.java.JobsList;


public class JobTest {
	protected final String TEST_FILE_1 = "./test1.txt";
	
	protected ArrayList<String> destinations;
	protected File file;
	
	@Before
	public void setup(){
		destinations = new ArrayList<String>();
		file = new File(TEST_FILE_1);
	}
	
	/**
	 * Tests for instantialization of jobs and adding them to a JobsList.
	 * Covers the requirements:
	 * Save Program State
	 * Schedule a Job
	 */
	@Test
	public void testAddInstantJobToList(){
		assertTrue("File name should exist", file.exists());
		
		destinations.add("./testdestination/testI1.txt");
		destinations.add("./testdestination/testI2.txt");
		
		Job job = new InstantJob(file, destinations);
		JobsList list = new JobsList(false); // false prevents JobsList from attempting to load prior version
		list.add(job);
		assertTrue(list.contains(job));
	}
	
	/**
	 * Tests for instantialization of jobs and adding them to a JobsList.
	 * Covers the requirements:
	 * Save Program State
	 * Schedule a Job
	 */
	@Test
	public void testAddRecurringJobToList(){
		assertTrue("File name should exist", file.exists());
		
		destinations.add("./testdestination/testR1.txt");
		destinations.add("./testdestination/testR2.txt");
		
		Job job = new InstantJob(file, destinations);
		JobsList list = new JobsList(false); // false prevents JobsList from attempting to load prior version
		list.add(job);
		assertTrue(list.contains(job));
	}
	
	@Test
	public void testLoadingJobFromXml(){
		
	}
	
	@Test
	public void testLoadingJobWhenNoXmlExists(){
		
	}
}
