package test.java;

import main.java.FileOperations;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


class FileOperationsTest {

	FileOperations operations;
	@Before
	void setUp() throws Exception {
		operations = new FileOperations();
	}

	@Test
	void testCreateFolder() {
		//TODO : create test file in project folder
		assertEquals(true, this.operations.createFolder("C:\\Users\\Dustin\\Desktop\\hellothere"));
	}
	
	@Test
	void testCompareHashes() {
		assertEquals(false, this.operations.compareHashes("036353ede97e766a573c73a6aae221fb", "3e1ffeb20ef5aa22c9aa1c0eca143acb"));
	}
	
	@Test
	void testdeleteFile() {
		//TODO : create test file in project folder
		assertEquals(true, this.operations.deleteFile("C:\\Users\\Dustin\\Desktop\\hellothere"));
	}

}
