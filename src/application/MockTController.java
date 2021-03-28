public class MockTController implements TController{
	private Trip[] threeTrips = new Trip[3];
	//trip 1
	private LocalDateTime checkIn1 = new LocalDateTime.of(2021, 6, 1, 11, 0);
	private LocalDateTime checkOut1 = new LocalDateTime.of(2021, 6, 1, 16, 0);
	//trip2
	private LocalDateTime checkIn2 = new LocalDateTime.of(2021, 6, 2, 11, 0);
	private LocalDateTime checkOut2 = new LocalDateTime.of(2021, 6, 2, 16, 0);
	//trip3
	private LocalDateTime checkIn3 = new LocalDateTime.of(2021, 6, 3, 11, 0);
	private LocalDateTime checkOut3 = new LocalDateTime.of(2021, 6, 3, 16, 0);
		
	public searchTrips(Parameters para) {
		Trip t1 = new Trip("ID1",0,70,1,[checkIn1,CheckOut1],"RVK");
		Trip t2 = new Trip("ID2",0, 10, 1, [checkIn2,CheckOut2],"RVK");
		Trip t3 = new Trip("ID3",0, 50, 1, [checkIn3,CheckOut3],"RVK");
		return Trip[] trips = [t1, t2, t3];
	}
}
