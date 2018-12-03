import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class HashFileUtilTest {
	HashFileUtil hasher;
	@BeforeEach
	void setUp() throws Exception {
		hasher = new HashFileUtil();
	}

	@Test
	void generateMd5Hashtest() {
		Assert.assertEquals(this.hasher.generateMd5Hash("C:\\Users\\Dustin\\Desktop\\hellothere"), this.hasher.generateMd5Hash("C:\\Users\\Dustin\\Desktop\\hellothere"));
	}
	
	@Test
	void generateMd5HashTestFile() {
		File file = new File("C:\\Users\\Dustin\\Desktop\\hellothere");
		Assert.assertEquals(this.hasher.generateMd5Hash(file), this.hasher.generateMd5Hash(file));
	}

}
