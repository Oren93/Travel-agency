/**
 * 
 */
package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

/**
 * @author Team T1
 *
 */
class ControllerTest {
	int difficulty;
	float [] priceRange;
	int capacity;
	LocalDateTime fromDate;
	LocalDateTime untilDate;
	static Parameters obj; // mock object
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("test started");
		obj = new Parameters ();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("test completed");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		fromDate = LocalDateTime.now().plusDays(5); // booking 5 days in advance
		untilDate = fromDate.plusWeeks(1); // duration of 1 week trip
		difficulty = 3;
		priceRange = new float [] {1000, 100000};
		capacity = 6;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	
	@Test
	void test() {
		assertEquals(LocalDateTime.now().plusDays(5+7),obj.getcheckOut());
	}

}
