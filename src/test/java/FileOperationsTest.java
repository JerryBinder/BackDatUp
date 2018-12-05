package test.java;

import main.java.FileOperations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Before;
import org.junit.Test;


public class FileOperationsTest {
	
	protected final String TEST_FILE_1 = "./test1.txt";
	protected final String TEST_FILE_2 = "/test2.txt";
	protected final String TEST_FOLDER = "./test/";

	FileOperations operations;
	@Before
	public void setUp() throws Exception {
		operations = new FileOperations();
	}

	@Test
	public void testCreateFolder() {
		assertEquals(true, this.operations.createFolder(TEST_FOLDER));
		
		// cleanup
		if(new File(TEST_FOLDER).exists())
			operations.deleteFile(TEST_FOLDER);
	}
	
	// This only tests a String comparison. Brings coverage % up but is otherwise useless.
	@Test
	public void testCompareHashes() {
		assertFalse(this.operations.compareHashes("036353ede97e766a573c73a6aae221fb", "3e1ffeb20ef5aa22c9aa1c0eca143acb"));
		assertTrue(this.operations.compareHashes("036353ede97e766a573c73a6aae221fb", "036353ede97e766a573c73a6aae221fb"));
	}
	
	@Test
	public void testdeleteFile() {
		assertEquals(true, this.operations.deleteFile(TEST_FILE_1));
		
		// cleanup - creates a text file to replace deleted one
		FileWriter fw;
		PrintWriter pw;
		try {
			fw = new FileWriter(TEST_FILE_1);
			pw = new PrintWriter(fw);
			pw.println("wandiawndowanofbaofhbasoif");
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
