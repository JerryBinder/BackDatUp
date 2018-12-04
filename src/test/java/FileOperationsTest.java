package test.java;

import main.java.FileOperations;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


class FileOperationsTest {
	
	protected final String TEST_FILE_1 = "/test1.txt";
	// protected final String TEST_FILE_2 = "/test2.txt";

	FileOperations operations;
	@Before
	void setUp() throws Exception {
		operations = new FileOperations();
	}

	@Test
	void testCreateFolder() {
		assertEquals(true, this.operations.createFolder(TEST_FILE_1));
	}
	
	// This only tests a String comparison. Brings coverage % up but is otherwise useless.
//	@Test
//	void testCompareHashes() {
//		assertEquals(false, this.operations.compareHashes("036353ede97e766a573c73a6aae221fb", "3e1ffeb20ef5aa22c9aa1c0eca143acb"));
//	}
	
	@Test
	void testdeleteFile() {
		assertEquals(true, this.operations.deleteFile(TEST_FILE_1));
	}

}
