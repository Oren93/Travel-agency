package application;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.lang.*;
import javafx.collections.*;


import daytour.*;
import flights.*;
import hotel.*;


class sController {
	ObservableList<HotelRoom> hotelRooms = FXCollections.observableArrayList();
	ObservableList<Flight> toFlights = FXCollections.observableArrayList();
	ObservableList<Flight> returnFlights = FXCollections.observableArrayList();
	ObservableList<Tour> tours = FXCollections.observableArrayList();
	//hotelRooms = HotelController.GetHotelRooms(searchParam); 
	
	//method to find the flight details 
	private ObservableList<Flight> searchCheapestFlights(Parameters parameters){
	    toFlights = FlightController.getAvailableFlights(parameters, true); //boolean variable true if flight is dearture to destination
	    returnFlights = FlightController.getAvailableFlights(parameters, false); 
	
	    double departPrice = Double.POSITIVE_INFINITY;
	    double returnPrice = Double.POSITIVE_INFINITY;
	
		Flight cheapDepart = null;
		Flight cheapReturn = null;

	    for (Flight flight : toFlights) {
			if (flight.getBasePrice() < departPrice) {
				cheapDepart = flight;
				departPrice = flight.getBasePrice();
			}
		}

		for (Flight flight : returnFlights) {
			if (flight.getBasePrice() < returnPrice) {
				cheapReturn =flight;
				returnPrice = flight.getBasePrice();
			}
		}

		ObservableList<Flight> cheapFlight = FXCollections.observableArrayList();
		cheapFlight.add(cheapDepart);
		cheapFlight.add(cheapReturn);

	    return cheapFlight;
	}

	private ObservableList<Flight> searchShortestFlights(Parameters parameters){
		double departTime = Double.POSITIVE_INFINITY;
	    double returnTime = Double.POSITIVE_INFINITY;
		
		Flight shortDepart = null;
		Flight shortReturn = null;

	    for (Flight flight : toFlights) {
			Duration duration = Duration.between(flight.getDateDepartTime(), flight.getDateArrivalTime());
	        if (duration.toMinutes() < departTime) {
	            shortDepart = flight;
	            departTime = duration.toMinutes();
	        }
	    }
		for (Flight flight : returnFlights) {
			Duration duration = Duration.between(flight.getDateDepartTime(), flight.getDateArrivalTime());
	        if (duration.toMinutes() < returnTime) {
	            shortReturn = flight;
	            returnTime = duration.toMinutes();
	        }
	    }  

		ObservableList<Flight> shortFlight = FXCollections.observableArrayList();
	    shortFlight.add(shortDepart);
    	shortFlight.add(shortReturn);

	    return shortFlight;
	}
	
	//method to find cheapest room for each day of trip 
	private HotelRoom searchCheapestRoom(Parameters parameters){
		// create array that holds in the available rooms 
		hotelRooms = HotelController.GetHotelRooms(parameters); 
	    HotelRoom cheapestRoom = null;
	    double roomPrice = Double.POSITIVE_INFINITY;
	    for (HotelRoom room : hotelRooms) {
			if (room.getPricePerNight() < roomPrice) {
				cheapestRoom = room;
				roomPrice = cheapestRoom.getPricePerNight();
				}
		}
	    return cheapestRoom;
	}

	private HotelRoom searchBestRoom(Parameters parameters){
		// create array that holds in the available rooms 
		ObservableList<HotelRoom> Rooms = HotelController.GetHotelRooms(parameters);
	    HotelRoom bestRoom = null;
	    int hotelStar = 0;
	    for (HotelRoom room : Rooms) {
			if (room.getHotelStar() <= hotelStar) bestRoom = room;
		}
	    return bestRoom;
	}

	 //Details for finding the convenient day tour. 
		private ObservableList<Tour> searchTours(Parameters parameters) {
			ObservableList<Tour> dTour =  dayTourC.searchTour(parameters);
			long voyageLength = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//total length in days of the trip. was long online, not sure if can be int
			ObservableList<Tour> tours = FXCollections.observableArrayList();
			for (long i=0; i<voyageLength; i++){
				int tourPrice = Integer.MAX_VALUE;
				Tour thisTour = null;
	            TourDate thisTourDate = null;
				for(Tour tour: dTour){
					if (tour.getPrice() <= tourPrice && parameters.getMaxPrice() >= tour.getPrice()) {
						for(TourDate tourDate : Tour.getDates()) {
							LocalDate today = parameters.getcheckIn().plusDays(i);
							if(today == tourDate.getDate().toLocalDate()) {
								thisTour = tour;
								tourPrice = tour.getPrice();
	                            thisTourDate = tourDate;
							}
						}
					}
				}
				tours.add(thisTour);
				dTour.remove(thisTour);
	            tourDates.add(thisTourDate);
				int low = parameters.getLowerPrice();
				int budget = parameters.getMaxPrice() - tourPrice;
				int[] price = new int[] {low,budget};
				parameters.setPrice(price);
			}
		}



	ObservableList<Package> search(Parameters parameters) {
		long voyageLength = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//total length in days of the trip. was long online, not sure if can be int
		TourPackage pCheapestFlights = null;
		TourPackage PShortesFlights = null;
		double budgetCheapF = parameters.getMaxPrice();
	    double budgetShortF = budgetCheapF;
	    ObservableList<Flight> cheapFlights = searchCheapestFlights(parameters);
	    ObservableList<Flight> shortFlights = searchShortestFlights(parameters);

		int cheapFlightsPrice = (cheapFlight.get(0).getBasePrice() + cheapFlight.get(1).getBasePrice());
		int shortFlightsPrice = (shortFlight.get(0).getBasePrice() + shortFlight.get(1).getBasePrice());

		budgetCheapF -= cheapFlightsPrice;
	    budgetShortF -= cheapFlightsPrice;
	    
		int low = parameters.getLowerPrice();

		int[] price = new int[] {low,(int)budgetCheapF};
		parameters.setPrice(price);
		HotelRoom cheapRoom = searchCheapestRoom(parameters);

		price[1] = (int)budgetShortF;
		parameters.setPrice(price);
		HotelRoom bestRoom = searchBestRoom(parameters);

		ObservableList<Tour> tours = searchTours(parameters);
		
		int toursPrice = 0;
		for(Tour tour: tours) {
			toursPrice += tour.getPrice();
		}

		int cheapPackagePrice = cheapFlightsPrice + cheapRoom.getPricePerNight() + toursPrice;
		int bestPackagePrice = shortFlightsPrice + bestRoom.getPricePerNight() + toursPrice;
		
		TourPackage cheapestPackage = new TourPackage (cheapFlights, cheapRoom, 
		tours, cheapPackagePrice)

		TourPackage bestPackage = new TourPackage (shortFlights, bestRoom, 
		tours, bestPackagePrice)

		ObservableList<TourPackage> packages = FXCollections.observableArrayList();
		packages.add(cheapestPackage);
		packages.add(bestPackage);

		return packages;
	}

	public static boolean bookPackage(TourPackage p, Passenger pass, Parameters para){
		boolean disability = false;
		if (para.getdifficulty() == 10) disability = true;
		ObservableList<Seat> availableSeatsDepart = getAvailableSeats(p.getFlights().get(0));
		ObservableList<Seat> availableSeatsReturn = getAvailableSeats(p.getFlights().get(1));

		ObservableList<Seat> SeatsDepart = FXCollections.observableArrayList();
		ObservableList<Seat> SeatsReturn = FXCollections.observableArrayList();

		for(int i = 0; i<para.getgroupSize(); i++) {
			SeatsDepart.add(availableSeatsDepart.get(i));
			SeatsReturn.add(availableSeatsReturn.get(i));
		}

		bookFlight(p.getFlights().get(0).getFlightNumber(), seatsDepart, pass, 0, 0, 0, 0, disability);
		bookFlight(p.getFlights().get(1).getFlightNumber(), seatsReturn, pass, 0, 0, 0, 0, disability);

		bookRoom(p.getRoom().getHotelName(), p, pass);

		for(Tour tour: p.tours) {
			ReservationController.confirmBooking(tour, TourDate tourDate, p.getgroupSize(), pass.getLastName(), String customerEmail);
		}
	}

}
