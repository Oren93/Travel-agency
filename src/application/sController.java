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
    	
    }
	 //Details for finding the convenient day tour. 
	//constructor 
    private void getTour(Parameters parameters) {
    	 ObservableList<Tour> dTour =  dayTourC.searchTour(parameters);
    }

	
	//method to find the flight details 
	Flight[] searchCheapFlight (Parameters parameters){
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
	    
	    double price1 = parameters.getMaxPrice();
	    double price2 = price1;
	
	    Flight[] cheapFlight; // cheapest departure Flight[0] + return flights Flight[1]
	    //Flight[] shortFlight; // same but for shorter flights
	
	    double departPrice = Double.POSITIVE_INFINITY;
	    double returnPrice = Double.POSITIVE_INFINITY;
	
	    //double departTime = Double.POSITIVE_INFINITY;
	    //double returnTime = Double.POSITIVE_INFINITY;
	
	    for(int i=0; i<Math.max(n,m); i++) {
	
	        if(i<n) {
	            if (departureF[i].getBasePrice() <= departPrice) {
	                cheapFlight[0] = departureF[i];
	                departPrice = departureF[i].getBasePrice();
	            }
	            //if (departureF[i].getDuration() <= departTime) { //need to see if Fteam can have duration as int (in minutes) or if provide departure and arrive LocalDateTime, we can create the variable in the loop
	            //    shortFlight[0] = Flight[i];
	            //    departTime = departureF[i].getDuration();
	            //}
	        }
	        if(i<m) {
	            if (returnF[i].getBasePrice() <= returnPrice) {
	                cheapFlight[1] = returnF[i];
	                returnPrice = returnF[i].getBasePrice();
	            }
	            //if (returnF[i].getDuration() <= returnTime) {
	            //    shortFlight[1] = Flight[i];
	            //    returnTime = returnF[i].getDuration();
	            //}
	        }
	
	    }   
	
	    price1 -= (departPrice + returnPrice);
	    //price2 -= (shortFLight[0].getPrice() + shortFLight[1].getPrice());
	    //return the array of flights
	    return cheapFlight;
	}

	Flight[] searchShortesFlight (Parameters parameters){
		Flight[] departureF = flightC.getAvailableFlights(parameters, true); //boolean variable true if flight is dearture to destination
	    Flight[] returnF = flightC.getAvailableFlights(parameters, false); //will return an Observable list, not arrray, need to check that
	    
	    int n = departureF.length;
	    int m = returnF.length;
	
	    long voyageLength = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//total length in days of the trip. was long online, not sure if can be int
	    
	    double price1 = parameters.getMaxPrice();
	    double price2 = price1;
	
	    Flight[] shortFlight; // shortest departure shortFlight[0] + return flights shortFlight[1]
	
	    double departTime = Double.POSITIVE_INFINITY;
	    double returnTime = Double.POSITIVE_INFINITY;

	    for(int i=0; i<Math.max(n,m); i++) {
			
	        if(i<n) {
				Duration duration = Duration.between(departureF[i].getDateDepartTime(), departureF[i].getDateArrivalTime());
	            if (duration.toMinutes() <= departTime) { //need to see if Fteam can have duration as int (in minutes) or if provide departure and arrive LocalDateTime, we can create the variable in the loop
	                shortFlight[0] = Flight[i];
	                departTime = departureF[i].getDuration();
	            }
	        }
	        if(i<m) {
	            if (returnF[i].getBasePrice() <= returnPrice) {
	                cheapFlight[1] = returnF[i];
	                returnPrice = returnF[i].getBasePrice();
	            }
	            //if (returnF[i].getDuration() <= returnTime) {
	            //    shortFlight[1] = Flight[i];
	            //    returnTime = returnF[i].getDuration();
	            //}
	        }
	
	    }   
	
	    price1 -= (departPrice + returnPrice);
	    //price2 -= (shortFLight[0].getPrice() + shortFLight[1].getPrice());
	    //return the array of flights
	    return cheapFlight;
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
