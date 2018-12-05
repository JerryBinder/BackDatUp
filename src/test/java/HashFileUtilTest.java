package test.java;

import main.java.HashFileUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Before;
import org.junit.Test;

public class HashFileUtilTest {
	
	protected final String TEST_FILE_1 = "./test1.txt";
	protected final String TEST_FILE_2 = "./test2.txt";
	
	protected HashFileUtil hasher;
	
	@Before
	public void setUp() throws Exception {
		hasher = new HashFileUtil();
	}

	/**
	 * Ensures hash method generates different hashes for different files.
	 */
	@Test
	public void generateMd5Hashtest() {
		assertNotEquals(this.hasher.generateMd5Hash(TEST_FILE_1), this.hasher.generateMd5Hash(TEST_FILE_2));
	}
	
	/**
	 * Ensures hash method consistently generates the same hash for the same file.
	 */
	@Test
	public void hashesAreConsistentTest() {
		assertEquals(this.hasher.generateMd5Hash(TEST_FILE_1), this.hasher.generateMd5Hash(TEST_FILE_1));
	}
}
