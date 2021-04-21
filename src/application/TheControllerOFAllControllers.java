/**
 * 
 */
package application;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
		returnFlights = FlightController.getAvailableFlights(p, true); 
		tours = TourController.searchTour(p); 
	}
	
	static ObservableList<TourPackage> findDeals (Parameters p) {
		ObservableList<ObservableList<Tour>> trips = FXCollections.observableArrayList();
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
					trips = makeUniquePlan(p.getcheckIn().plusDays(1),budget,days);
					for (ObservableList<Tour> lis : trips)
						TP.add(new TourPackage(f,r,h,lis));
						
					/*
					 *     TourPackage (Flight from, Flight to, HotelRoom h,
    		ObservableList<Tour> d)*/
				}
			}
		}
		int k = TP.size();
		for (int i = 0 ; i < k ; i++)
			for (int j = i+1 ;j < k ; )
				if (TP.get(i).equals(TP.get(j))) {
					TP.remove(j);
					k--;
				}
				else j++;
		return TP;		
	}
	private static ObservableList<ObservableList<Tour>> makeUniquePlan(LocalDate d,int budget, int days){
		int lim = tours.size();
		 ObservableList<ObservableList<Tour>> matrix = FXCollections.<ObservableList<Tour>>observableArrayList();
		 int currentDay = 0;
		 int price = 0;
		 for (int i = 0; i < lim-days; i++) {
		     ObservableList<Tour> row = FXCollections.<Tour>observableArrayList();
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
		 return matrix;
	}
}
