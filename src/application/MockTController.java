public class MockTController implements TController{
	private Trip[] threeTrips = new Trip[3];
	//trip 1
	private LocalDateTime checkIn1 = new LocalDateTime.of(2021, 6, 1, 5, 5);
	private LocalDateTime checkOut1 = new LocalDateTime.of(2021, 6, 1, 5, 8);
	//trip2
	private LocalDateTime checkIn2 = new LocalDateTime.of(2021, 6, 1, 6, 5);
	private LocalDateTime checkOut2 = new LocalDateTime.of(2021, 6, 1, 6, 8);
	//trip3
	private LocalDateTime checkIn3 = new LocalDateTime.of(2021, 6, 1, 7, 5);
	private LocalDateTime checkOut3 = new LocalDateTime.of(2021, 6, 1, 7, 8);
		
	public searchTrips(Parameters para) {
		Trip t1 = new trip("ID1",0,70,1,[checkIn1,CheckOut1],"RVK");
		Trip t2 = new trip("ID2",0, 10, 1, [checkIn2,CheckOut2],"RVK");
		Trip t3 = new trip("ID3",0, 50, 1, [checkIn3,CheckOut3],"RVK");
		return Trip[] trips = [t1, t2, t3];
	}
}
