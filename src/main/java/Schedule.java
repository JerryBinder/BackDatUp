package main.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jerry Binder
 * Schedule containing all Jobs.
 */
@XmlRootElement(name="Schedule")
public class Schedule {
	
	protected final static String SERIALIZATION_PATH = "schedule.xml";
	private List<Job> jobs;
	
	private static class SingletonHelper{
		private static final Schedule INSTANCE = new Schedule().loadFromXml(SERIALIZATION_PATH);
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
	 * Deletes a job from the schedule.
	 * @param sourceFile
	 * @return false if job doesn't exist, true if it was deleted
	 */
	public boolean deleteJob(String sourceFile){
		for(Job j : jobs){
			if(j.getSourceFile() == sourceFile){
				jobs.remove(j);
				serializeInXml();
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
		serializeInXml();
	}
	
	@XmlElement(name="Jobs")
	public List<Job> getJobs(){
		return jobs;
	}
	
	/**
	 * Serializes the current Schedule in an XML file.
	 * Called by addJob and removeJob.
	 */
	private void serializeInXml(){
		// deletes old file (if it exists) to make sure removed jobs don't sneak back in
		FileOperations.getInstance().deleteFile(SERIALIZATION_PATH);
		
		try{
			// uses @XmlElement tags to create XML out of Schedule and its Jobs
			JAXBContext context = JAXBContext.newInstance(Schedule.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter writer = new StringWriter();
			m.marshal(jobs,  writer);
			String result = writer.toString();
			
			// writes XML to file
			FileWriter fw= new FileWriter(SERIALIZATION_PATH);
			fw.write(result);
			fw.close();
		} catch(IOException | JAXBException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the contents of the schedule XML file into the Schedule singleton.
	 * @param serializationPath
	 * @return Schedule
	 */
	private Schedule loadFromXml(String serializationPath) {
		FileReader fr = null;
		char[] buffer = new char[1024];
		StringBuffer fileContent = new StringBuffer();
		try {
			fr = new FileReader(SERIALIZATION_PATH);
			int i = 0;
			while((i = fr.read(buffer)) != -1){
				fileContent.append(new String(buffer));
			}
			fr.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
		StringReader reader = new StringReader(fileContent.toString());
		
		try {
			JAXBContext context = JAXBContext.newInstance(Schedule.class);
			Unmarshaller un = context.createUnmarshaller();
			return (Schedule)un.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
}
