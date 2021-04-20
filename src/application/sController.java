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
	
	private FlightController flightC;
	private HotelController hotelC;
	private TourController dayTourC;
	
	//constructor
    sController() {
		flightC = new FlightController();
		hotelC = new HotelController();
		dayTourC = new TourController();
    }
	
	//method to find the flight details 
	private ObservableList<Flight> searchCheapestFlights(Parameters parameters){
	    ObservableList<Flight> departureF = flightC.getAvailableFlights(parameters, true); //boolean variable true if flight is dearture to destination
	    ObservableList<Flight> returnF = flightC.getAvailableFlights(parameters, false); //will return an Observable list, not arrray, need to check that
	
	    double departPrice = Double.POSITIVE_INFINITY;
	    double returnPrice = Double.POSITIVE_INFINITY;
	
		Flight cheapDepart = null;
		Flight cheapReturn = null;

	    for (Flight flight : departureF) {
			if (flight.getBasePrice() <= departPrice) {
				cheapDepart = flight;
				departPrice = flight.getBasePrice();
			}
		}

		for (Flight flight : returnF) {
			if (flight.getBasePrice() <= returnPrice) {
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
		ObservableList<Flight> departureF = flightC.getAvailableFlights(parameters, true); //boolean variable true if flight is dearture to destination
	    ObservableList<Flight> returnF = flightC.getAvailableFlights(parameters, false); //will return an Observable list, not arrray, need to check that
	    

		double departTime = Double.POSITIVE_INFINITY;
	    double returnTime = Double.POSITIVE_INFINITY;
		
		Flight shortDepart = null;
		Flight shortReturn = null;

	    for (Flight flight : departureF) {
			Duration duration = Duration.between(flight.getDateDepartTime(), flight.getDateArrivalTime());
	        if (duration.toMinutes() <= departTime) { //need to see if Fteam can have duration as int (in minutes) or if provide departure and arrive LocalDateTime, we can create the variable in the loop
	            shortDepart = flight;
	            departTime = duration.toMinutes();
	        }
	    }
		for (Flight flight : returnF) {
			Duration duration = Duration.between(flight.getDateDepartTime(), flight.getDateArrivalTime());
	        if (duration.toMinutes() <= returnTime) {
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
		ObservableList<HotelRoom> Rooms = hotelC.GetHotelRooms(parameters);
	    HotelRoom cheapestRoom = null;
	    double roomPrice = Double.POSITIVE_INFINITY;
	    for (HotelRoom room : Rooms) {
			if (room.getPricePerNight() <= roomPrice) cheapestRoom = room;
		}
	    return cheapestRoom;
	}

	private HotelRoom searchBestRoom(Parameters parameters){
		// create array that holds in the available rooms 
		ObservableList<HotelRoom> Rooms = hotelC.GetHotelRooms(parameters);
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
			for(Tour tour: dTour){
				if (tour.getPrice() <= tourPrice && parameters.getMaxPrice() >= tour.getPrice()) {
					for(TourDate tourDate : Tour.getDates()) {
						LocalDate today = parameters.getcheckIn().plusDays(i);
						if(today == tourDate.getDate().toLocalDate()) {
							thisTour = tour;
							tourPrice = tour.getPrice();
						}
					}
				}
			}
			tours.add(thisTour);
			dTour.remove(thisTour);

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
