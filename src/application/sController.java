package application;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.lang.*;
import javafx.collections.*;

import daytour.*;
import flights.*;
//import daytour.fakeData.*;
//import 3H.*;


class sController {
	
	private FlightController flightC;
//	private hController hotelC;
	private TourController dayTourC;
	
	//constructor
    sController() {
		flightC = new FlightController();
//		hotelC = new hController(); //need  to resolve hController name
		dayTourC = new TourController();
    }

	 //Details for finding the convenient day tour. 
    private void getTour(Parameters parameters) {
    	 ObservableList<Tour> dTour =  dayTourC.searchTour(parameters);
		 long voyageLength = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//total length in days of the trip. was long online, not sure if can be int
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
	Room[] searchRoom (Parameters parameters){
		// create array that holds in the available rooms 
	    //Room [] availableRoom; 
	    //Room [] cheaprooms;
	    //Room cheapestRoom ;
	    //double roomPrice = Double.POSITIVE_INFINITY;
	    for(long i=0; i<voyageLength; i++) {
	        //create cheapestRoom array of length voyageLength, fill it with cheapest room for each night
	    	//voyageLength =  total length of trip= number of days 
	    	availableRoom = hcontroller.searchRooms(i);
	    	for(int j=0; i<availableRoom.length; j++) {
	    		if (roomPrice > availableRoom[j].getbasePrice())
	    			cheapestRoom = availableRoom[j] ;
	    	}
	    	price1 -= cheapestRoom.getbasePrice();
	    	price2 -= cheapestRoom.getbasePrice();
	    	
	    }
	    return cheapestRoom[];
	}

	Package[] search(Parameters parameters) {
		long voyageLength = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//total length in days of the trip. was long online, not sure if can be int
		TourPackage pCheapestFlights;
		TourPackage PShortesFlights;
		double priceCheapF = parameters.getMaxPrice();
	    double priceShortF = priceCheapF;
	    ObservableList<Flight>  cheapFlight = searchCheapestFlights(parameters);
	    ObservableList<Flight>  shortFlight = searchShortestFlights(parameters);
		priceCheapF -= (cheapFlight.get(0).getBasePrice() + cheapFlight.get(1).getBasePrice());
	    priceShortF -= (shortFlight.get(0).getBasePrice() + shortFlight.get(1).getBasePrice());
	}
	
}
