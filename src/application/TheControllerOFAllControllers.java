/**
 * 
 */
package application;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import application.Parameters;
import daytour.*;
import flights.*;
import hotel.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Oren1
 *
 */
public class TheControllerOFAllControllers {
	static ObservableList<HotelRoom> hotelRooms = FXCollections.observableArrayList();
	static ObservableList<Flight> toFlights = FXCollections.observableArrayList();
	static ObservableList<Flight> returnFlights = FXCollections.observableArrayList();
	static ObservableList<Tour> tours = FXCollections.observableArrayList();
	//hotelRooms = HotelController.GetHotelRooms(searchParam); 
	
	private static void initData (Parameters p) {
		hotelRooms = HotelController.GetHotelRooms(p); 
		toFlights = FlightController.getAvailableFlights(p, true); 
		returnFlights = FlightController.getAvailableFlights(p, false); 
		tours = TourController.searchTour(p); 
		System.out.println("tours.size() "+tours.size());
		System.out.println("toFlights.size() "+toFlights.size());
		System.out.println("returnFlights.size() "+returnFlights.size());
		System.out.println("hotelRooms.size() "+hotelRooms.size());
	}
	
	static ObservableList<TourPackage> findDeals (Parameters p) {
		//ObservableList<ObservableList<Tour>> trips = FXCollections.observableArrayList();
		ObservableList<Tour> trips = FXCollections.observableArrayList();
		ObservableList<TourPackage> TP = FXCollections.observableArrayList();
        int days = (int) p.getcheckIn().until(p.getcheckOut(), ChronoUnit.DAYS)-2;
		initData(p);
		int budget = p.getMaxPrice();
		int tooCheap = p.getLowerPrice();
		ObservableList<TourPackage> list = FXCollections.observableArrayList();
		for (HotelRoom h : hotelRooms) {
	        budget -= (days-1) * h.getPricePerNight();
			for (Flight f : toFlights) {
				budget -= f.getBasePrice();
				for (Flight r : returnFlights) {
					budget -= r.getBasePrice();
					//trips = makeUniquePlan(p.getcheckIn().plusDays(1),budget,4);
					trips = searchTours(p.getcheckIn().plusDays(1),budget,4);
					// the number 4 is for testing, in reality it'd be called:
					//trips = makeUniquePlan(p.getcheckIn().plusDays(1),budget,days);
					//for (ObservableList<Tour> lis : trips)
						//TP.add(new TourPackage(f,r,h,lis));
				}
			}
		}
		int k = TP.size();
		/*for (int i = 0 ; i < k ; i++) {
			 System.out.println(i+". i TESTING...........");
			for (int j = i+1 ;j < k ; ) {
				 System.out.println(j+". j TESTING...........");
				if (TP.get(i).equals(TP.get(j))) {
					TP.remove(j);
					k--;
				}
				else j++;
			}
		}*/
		return TP;		
	}

	
	 //Details for finding the convenient day tour. 
		private static ObservableList<Tour> searchTours(LocalDate d,int budget, int days) {
			ObservableList<Tour> dTour =  tours;
			long voyageLength = (long) days;// = parameters.getcheckIn().until(parameters.getcheckOut(), ChronoUnit.DAYS);//total length in days of the trip. was long online, not sure if can be int
			ObservableList<Tour> toReturn = FXCollections.observableArrayList();
			//ObservableList<TourDate> tourDates = FXCollections.observableArrayList();
			for (long i=0; i<voyageLength; i++){
				int tourPrice = Integer.MAX_VALUE;
				Tour thisTour = null;
	            //TourDate thisTourDate = null;
				for(Tour tour: dTour){
					System.out.println("for");
					if (tour.getPrice() <= tourPrice && budget >= tour.getPrice()) {
						System.out.println("if1");
						thisTour = tour;
						tourPrice = tour.getPrice();
	                    //thisTourDate = tourDate;
					}
				}
				toReturn.add(thisTour);
				dTour.remove(thisTour);
	            //tourDates.add(thisTourDate);
				int low =0;
				int budg = budget - tourPrice;
				int[] price = new int[] {low,budget};
				//parameters.setPrice(price);
			}
			return toReturn;
		}

	private static ObservableList<ObservableList<Tour>> makeUniquePlan(LocalDate d,int budget, int days){
		 int lim = tours.size();
		 ObservableList<ObservableList<Tour>> matrix = FXCollections.<ObservableList<Tour>>observableArrayList();
		 int currentDay = 0;
		 int price = 0;
		 for (int i = 0; i <= lim-days; i++) {
		     ObservableList<Tour> row = FXCollections.<Tour>observableArrayList();
			 System.out.println(tours.get(0).getDates().toString());
		     if (tours.get(i).getDates().contains(d.plusDays(currentDay))) {
		    	 row.add(tours.get(i));
		    	 currentDay++;
		    	 price += tours.get(i).getPrice();
		     }
		     for (int j = i; j < lim; j++) {
			     if (tours.get(j).getDates().contains(d.plusDays(currentDay))) {
			    	 row.add(tours.get(j));
			    	 currentDay++;
			    	 price += tours.get(j).getPrice();
			    	 if (currentDay >= days)
			    		 break;
			     }
		     }
		     if (price < budget)
		    	 matrix.add(i, row);
		     price = 0;
		 }
		 //System.out.println("Package found: "+matrix.size());
		 return matrix;
	}
	boolean bookPackage(TourPackage p, Passenger pass, Parameters para){
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

		flightC.bookFlight(p.getFlights().get(0).getFlightNumber(), SeatsDepart, pass, 0, 0, 0, 0, disability);
		flightC.bookFlight(p.getFlights().get(1).getFlightNumber(), SeatsReturn, pass, 0, 0, 0, 0, disability);

		hotelC.bookRoom(p.getRoom().getHotelName(), p, pass);

		String customerEmail = "";

		for(int i=0; i<tourDates.size(); i++) { //tourDates needs to be kept somewhere
			ReservationController.confirmBooking(p.tours.get(i), tourDates.get(i), p.getgroupSize(), pass.getLastName(), customerEmail);
		}
	}
}
