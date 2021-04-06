import static org.junit.Assert.assertTrue;

public class TestControllerSearch {
@Before
public void setUp() { 
	MockTController tController = new MockTController();
	MockHController hController = new MockHController();
	MockFController fController = new MockFController();
	SearchController sController = new SearchController();
	LocalDateTime checkIn = new LocalDateTime.of(2021, 6, 1, 5, 0);
	LocalDateTime checkOut = new LocalDateTime.of(2022, 6, 1, 5, 0);
	Parameters validPara = (0,[0,100], 1, [checkIn, CheckOut],"London","RVK");
}

@After
public void tearDown() {
	tController = null;
	hController = null;
	fController = null;
	sController = null;
	LocalDateTime checkIn = null;
	LocalDateTime checkOut = null;
	Parameters validPara = null;
}

@Test
public void testSearchParaNull() {	
	assertNull(sController.search())
}

@Test
public void testSearchParaType() {	
	int i = 0;
	assertNull(sController.search(i))
}

@Test
public void testDates() {	
	Parameters invalidDatesPara = (0,[0,100], 1, [checkOut, CheckIn],"London","RVK")
	assertNull(sController.search(wrongDatesPara))
}

@Test
public void testReturns() {	
	assertNotNull(sController.search(validPara));
}

@Test
public void testReturnType() {	
	assertTrue(sController.search(validPara) instanceof Package[]);
}

@Test
public void testTripsSameAsMock() {	
	for(i=0; i<sController.search(validPara).length; i++){
		asserTrue(Arrays.asList(MockTController.searchTrips(validPara)).containsAll(Arrays.asList(sController.search(validPara)[i]).Trips));
	}
}

@Test
public void testFlightsSameAsMock() {	
	for(i=0; i<sController.search(validPara).length; i++){
		asserTrue(Arrays.asList(MockFController.searchFlights(validPara)).containsAll(Arrays.asList(sController.search(validPara)[i]).Flights));
	}
}

@Test
public void testRoomsSameAsMock() {	
	for(i=0; i<sController.search(validPara).length; i++){
		asserTrue(Arrays.asList(MockHController.searchRooms(validPara)).containsAll(Arrays.asList(sController.search(validPara)[i]).Rooms));
	}
}

}
