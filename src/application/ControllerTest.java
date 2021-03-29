/**
 * 
 */
package application;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
	static Parameters obj2; // mock object
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("test started");
		obj = new Parameters (); // Instantiate empty search object
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
    @DisplayName("Check if checkout date and time was set correctly")
	void testCheckout() {
		assertEquals(LocalDateTime.now().plusDays(5+7),obj.getcheckOut());
	}
	
	@Test
    @DisplayName("Search object's date should not be yesterday")
	void testCheckIn() {
		LocalDateTime yesterday = LocalDateTime.now().minusDays(1);
		obj.setCheckIn(yesterday); // shouldn't get accepted 
		assertFalse(obj.getcheckIn().isEqual(yesterday)); 
	}

	@Test
    @DisplayName("Check for unrealistic price")
	void testprice() {
		obj.setPrice(new float [] {-10427,0});
		assertFalse(obj.getLowerPrice() == -10427); 
	}

	@Test
    @DisplayName("check the results from the flight teams")
	void dateInRange() {
		LocalDateTime [] dates = new LocalDateTime [] {LocalDateTime.now().plusMonths(3),
				LocalDateTime.now().plusMonths(3).plusWeeks(2)};
		float [] prices = new float [] {1024, 999999};
		obj2 = new Parameters (1, prices, 2, dates);
		/* below method doesn't exist yet, the idea is to get an array of all the
		 * flights that are matching the search parameters and store them in an array
		 * of flights - inherit from class Parameters */ 
		Flight [] flights = 3F.Flight.search(obj2);
		/* next we make sure the first flight found departs within the date interval given*/
		assertTrue(flights[0].getCheckIn().isAfter(dates[0]) &&
				flights[0].getCheckIn().isBefore(dates[1]));
		
	}

	@Test
    @DisplayName("check the results from the trip teams")
	void considerDisability() {
		obj2.setDifficulty(0); // 0 difficulty level means fits for disabled
		/* below method doesn't exist yet*/
		 dayTrip [] trips = 3D.Trips.search(obj2);
		/* next we make sure the first trip in the returned trip arrays fits the criteria */
		assertTrue(0,trips[0].getDifficulty);
	}

}
