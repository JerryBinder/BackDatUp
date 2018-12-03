package test.java;

import java.io.File;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.java.HashFileUtil;

class HashFileUtilTest {
	
	HashFileUtil hasher;
	
	@Before
	void setUp() throws Exception {
		hasher = new HashFileUtil();
	}

	@Test
	void generateMd5Hashtest() {
		//TODO : create test file in project folder
		assertEquals(this.hasher.generateMd5Hash("C:\\Users\\Dustin\\Desktop\\hellothere"), this.hasher.generateMd5Hash("C:\\Users\\Dustin\\Desktop\\hellothere"));
	}
	
	@Test
	void generateMd5HashTestFile() {
		//TODO : create test file in project folder
		File file = new File("C:\\Users\\Dustin\\Desktop\\hellothere");
		assertEquals(this.hasher.generateMd5Hash(file), this.hasher.generateMd5Hash(file));
	}

}
