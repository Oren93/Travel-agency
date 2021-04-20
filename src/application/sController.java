package application;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.lang.*;
import javafx.collections.ObservableList;

import daytour.application.*;
import daytour.controller.*;
import daytour.data.*;
import daytour.Model.*;
import flights.*;
//import daytour.fakeData.*;
//import 3H.*;


class sController {
	
	private FlightController flightC;
//	private hController hotelC;
	private TourController dayTourC;
	
	
    sController() {
		flightC = new FlightController();
//		hotelC = new hController(); //need  to resolve hController name
		dayTourC = new TourController();
    }
	 //Details for finding the convenient day tour. 
	//constructor 
    private void getTour(Parameters parameters) {
    	 ObservableList<Tour> dTour =  dayTourC.searchTour(parameters);
    }

	
	//method to find the flight details 
	private Flight[] searchCheapestFlights(Parameters parameters){
	    Flight[] departureF = flightC.getAvailableFlights(parameters, true); //boolean variable true if flight is dearture to destination
	    Flight[] returnF = flightC.getAvailableFlights(parameters, false); //will return an Observable list, not arrray, need to check that
	    
		// create array that holds in the available rooms 
	    //Room [] availableRoom; 
	    //Room [] cheaprooms;
	    //Room cheapestRoom ;
	    //double roomPrice = Double.POSITIVE_INFINITY;
	    
	    int n = departureF.length;
	    int m = returnF.length;
	
	    long voyageLength = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//total length in days of the trip. was long online, not sure if can be int
	
	    Flight[] cheapFlight; // cheapest departure Flight[0] + return flights Flight[1]
	
	    double departPrice = Double.POSITIVE_INFINITY;
	    double returnPrice = Double.POSITIVE_INFINITY;
	
	    for(int i=0; i<Math.max(n,m); i++) {
	
	        if(i<n) {
	            if (departureF[i].getBasePrice() <= departPrice) {
	                cheapFlight[0] = departureF[i];
	                departPrice = departureF[i].getBasePrice();
	            }
	        }
	        if(i<m) {
	            if (returnF[i].getBasePrice() <= returnPrice) {
	                cheapFlight[1] = returnF[i];
	                returnPrice = returnF[i].getBasePrice();
	            }
	        }
	
	    }   
	    return cheapFlight;
	}

	private Flight[] searchShortestFlights(Parameters parameters){
		Flight[] departureF = flightC.getAvailableFlights(parameters, true); //boolean variable true if flight is dearture to destination
	    Flight[] returnF = flightC.getAvailableFlights(parameters, false); //will return an Observable list, not arrray, need to check that
	    
	    int n = departureF.length;
	    int m = returnF.length;
	
	    long voyageLength = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//total length in days of the trip. was long online, not sure if can be int
	
	    Flight[] shortFlight; // shortest departure shortFlight[0] + return flights shortFlight[1]
	
	    double departTime = Double.POSITIVE_INFINITY;
	    double returnTime = Double.POSITIVE_INFINITY;

	    for(int i=0; i<Math.max(n,m); i++) {
			
	        if(i<n) {
				Duration duration = Duration.between(departureF[i].getDateDepartTime(), departureF[i].getDateArrivalTime());
	            if (duration.toMinutes() <= departTime) { //need to see if Fteam can have duration as int (in minutes) or if provide departure and arrive LocalDateTime, we can create the variable in the loop
	                shortFlight[0] = shortFlight[0];
	                departTime = duration.toMinutes();
	            }
	        }
	        if(i<m) {
				Duration duration = Duration.between(departureF[i].getDateDepartTime(), departureF[i].getDateArrivalTime());
	            if (duration.toMinutes() <= returnTime) {
	                shortFlight[1] = shortFlight[1];
	                returnTime = duration.toMinutes();
	            }
	        }
	
	    }   
	    //return the array of flights
	    return shortFlight;
	}
	
	//method to find cheapest room for each day of trip 
	Room[] searchRoom (Parameters parameters){
		
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
	//method to find convinient tours for each day 
	Tour[] searchDayTour (Parameters parameters){
		
	}

	Package[] search(Parameters parameters) {
		TourPackage P1;
		TourPackage P2;
		double priceCheapF = parameters.getMaxPrice();
	    double priceShortF = priceCheapF;
		Flight[] cheapFlight = searchCheapestFlights(parameters);
		Flight[] shortFlight = searchShortestFlights(parameters);
		priceCheapF -= (cheapFlight[0].getBasePrice() + cheapFlight[1].getBasePrice());
	    priceShortF -= (shortFlight[0].getBasePrice() + shortFlight[1].getBasePrice());
	}
	
}
