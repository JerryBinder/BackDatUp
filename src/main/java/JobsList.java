package main.java;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({Job.class, InstantJob.class, RecurringJob.class})
public class JobsList extends ArrayList<Job> {
	private static final long serialVersionUID = -4941703133518352572L;
	protected final static String SERIALIZATION_PATH = "./jobs.xml";
	
	// exists to make XML loader happy - don't call it
	public JobsList(){}
	
	public JobsList(boolean shouldLoadFromFile){
		if(shouldLoadFromFile)
			addAll(loadFromXml());
	}
	
	@XmlElement(name = "Job")
	public List<Job> getJobs(){
		return this;
	}
	
	@Override
	public boolean add(Job job){
		super.add(job);
		serializeInXml();
		return true;
	}
	
//	@Override
	public boolean remove(Job job){
		super.remove(job);
		serializeInXml();
		return true;
	}
	
	/**
	 * Serializes the current JobsList in an XML file.
	 * Called by addJob and removeJob.
	 */
	private void serializeInXml(){
		System.out.println("calling serialize");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// deletes old file (if it exists) to make sure removed jobs don't sneak back in
		File temp = new File(SERIALIZATION_PATH);
		if(temp.exists())
			temp.delete();
		if(temp.exists()){
			System.out.println("delete failed");
			return;
		}
		//FileOperations.getInstance().deleteFile(SERIALIZATION_PATH);	// TODO fix this
		
		try{
			// uses @XmlElement tags to create XML out of JobsList and its Jobs
			JAXBContext context = JAXBContext.newInstance(JobsList.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter writer = new StringWriter();
			m.marshal(this,  writer);
			String result = writer.toString();
			
			// writes XML to file
			FileWriter fw= new FileWriter(SERIALIZATION_PATH);
			fw.write(result);
			fw.close();
		} catch(IOException | JAXBException e){
			e.printStackTrace();
		}
		System.out.println("serialize done");
	}
	
	/**
	 * Loads the contents of the JobsList XML file into the object.
	 * @param serializationPath
	 * @return Schedule
	 */
	private JobsList loadFromXml() {
		// if XML file doesn't exist, JobsList class returns its empty self
		System.out.println("starting load");
		if(!(new File(SERIALIZATION_PATH).exists())){
			return new JobsList(false);
		}
		System.out.println("path doesn't exist, continuing");
		
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
			JAXBContext context = JAXBContext.newInstance(JobsList.class);
			Unmarshaller un = context.createUnmarshaller();
			return (JobsList)un.unmarshal(reader);	// TODO fix nullpointerexception here
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
}
