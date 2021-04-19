package application;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.lang.*;

import daytour.*;
import 3H.*;
import flights.*;


class sController {
	
	private FlightController flightC;
	private hController hotelC;
	private TourController dayTourC;
	
	
    sController() {
		flightC = new FlightController();
		hotelC = new hController(); //need  to resolve hController name
		dayTourC = new TourController();
    }

	private void getFlights(Parameters parameters) {

	}

	 //Details for finding the convenient day tour. 
	//constructor 
    private void getTour(Parameters parameters) {
    	 ObservableList<Tour> dTour =  dController.TourController.searchTours( parameters);
    }

	
	//method to find the flight details 
	Flight[] searchFlight (Parameters parameters){
	    Flight[] departureF = flightC.getAvailableFlights(parameters, true); //boolean variable true if flight is dearture to destination
	    Flight[] returnF = flightC.getAvailableFlights(parameters, false); //will return an Observable list, not arrray, need to check that
	    // create array that holds in the available rooms 
	    Room [] availableRoom; 
	    Room [] cheaprooms;
	    Room cheapestRoom ;
	    double roomPrice = Double.POSITIVE_INFINITY;
	    
	   
	    
	    int n = departureF.length;
	    int m = returnF.length;
	
	    long voyageLength = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//total length in days of the trip. was long online, not sure if can be int
	    
	    double price1 = parameters.getPrice();
	    double price2 = parameters.getPrice();
	
	    Flight[] cheapFlight; // cheapest departure Flight[0] + return flights Flight[1]
	    Flight[] shortFlight; // same but for shorter flights
	
	    double departPrice = Double.POSITIVE_INFINITY;
	    double returnPrice = Double.POSITIVE_INFINITY;
	
	    double departTime = Double.POSITIVE_INFINITY;
	    double returnTime = Double.POSITIVE_INFINITY;
	
	    for(int i=0; i<Math.max(n,m); i++) {
	
	        if(i<n) {
	            if (departureF[i].getbasePrice() <= departPrice) {
	                cheapFlight[0] = Flight[i];
	                departPrice = departureF[i].getbasePrice();
	            }
	            if (departureF[i].getDuration() <= departTime) { //need to see if Fteam can have duration as int (in minutes) or if provide departure and arrive LocalDateTime, we can create the variable in the loop
	                shortFlight[0] = Flight[i];
	                departTime = departureF[i].getDuration();
	            }
	        }
	        if(i<m) {
	            if (returnF[i].getbasePrice() <= returnPrice) {
	                cheapFlight[1] = Flight[i];
	                returnPrice = returnF[i].getbasePrice();
	            }
	            if (returnF[i].getDuration() <= returnTime) {
	                shortFlight[1] = Flight[i];
	                returnTime = returnF[i].getDuration();
	            }
	        }
	
	    }   
	
	    price1 -= (departPrice + returnPrice);
	    price2 -= (shortFLight[0].getPrice() + shortFLight[1].getPrice());
	    //return the array of flights
	    return searchFlight[];
	}
	//here we can create a separate method to return cheapest flight
	Flight[] searchCheapestFlight (Parameters parameters){
		
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
	
}
