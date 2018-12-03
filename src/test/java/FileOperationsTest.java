import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class FileOperationsTest {

	FileOperations operations;
	@BeforeEach
	void setUp() throws Exception {
		operations = new FileOperations();
	}

	@Test
	void testCreateFolder() {
		//fail("Not yet implemented");
		Assert.assertEquals(true, this.operations.createFolder("C:\\Users\\Dustin\\Desktop\\hellothere"));
	}
	
	@Test
	void testCompareHashes() {
		Assert.assertEquals(false, this.operations.compareHashes("036353ede97e766a573c73a6aae221fb", "3e1ffeb20ef5aa22c9aa1c0eca143acb"));
	}
	
	@Test
	void testdeleteFile() {
		Assert.assertEquals(true, this.operations.deleteFile("C:\\Users\\Dustin\\Desktop\\hellothere"));
	}

}
