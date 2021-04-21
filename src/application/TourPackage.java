package application;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import daytour.*;
import hotel.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import flights.*;

class TourPackage {
    //String packageID;
	private ObservableList<Flight> flights;
    private HotelRoom room;
    private ObservableList<Tour> tours;
    private LocalDate dayOne;
    private LocalDate LastDay;

    /**
	 * Constructor for an object type TourPackage
	 * @param t array of tours contained in the TourPackage 
	 * @param f array of flights contained in the TourPackage
	 * @param r array of rooms contained in the TourPackage
	 * @param p array of price contained in the TourPackage
	 */
    TourPackage (ObservableList<Flight> f, HotelRoom h,
    		ObservableList<Tour> d)
    {
    	flights = f;
    	room = h;
    	tours = d;
    	dayOne = flights.get(0).getDateArrivalTime().toLocalDate();
    	LastDay = flights.get(1).getDateArrivalTime().toLocalDate();
    }
    TourPackage (Flight from, Flight to, HotelRoom h,
    		ObservableList<Tour> d)
    {
    	flights.add(from);
    	flights.add(to);
    	room = h;
    	tours = d;
    	dayOne = flights.get(0).getDateArrivalTime().toLocalDate();
    	LastDay = flights.get(1).getDateArrivalTime().toLocalDate();
    }

    /* set methods
    //private void setTourPackageID() {
    //    String packID = ; // add here math.Random to create TourPackage ID according to format
    //    TourPackageID = packID;
    }*/

    private void setTour(Tour tr, int day) {
    	if (day >0 && dayOne.plusDays((long)day).isBefore(LastDay))
    		tours.set(day, tr);
    }

    private void setFlight(Flight fli,boolean depart) {
        if(depart) {
            flights.set(0, fli);
        }
        flights.set(1, fli);
    }

    private void setRoom(HotelRoom rs) {
        room = rs;
    }

    ObservableList<Tour> getTours() {
        return tours;
    }

    ObservableList<Flight> getFlights() {
        return flights;
    }

    HotelRoom getRoom() {
        return room;
    }

    double getPrice() {
        int days = (int) dayOne.until(LastDay, ChronoUnit.DAYS);
        int price = (days-1) * room.getPricePerNight();
        price += flights.get(0).getBasePrice()+flights.get(1).getBasePrice();
        for (Tour t : tours)
        	price += t.getPrice();
        return price;
    }
    /**
     * Checks if two packages are not the same, might return false positive, 
     * meaning that if returns true there is still a change the packages aren't equal
     * @param other TourPackage
     * @return false if definitely not the same, true if likely to be equal
     */
    boolean equals (TourPackage other) {
    	if (!flights.equals(other.getFlights()))
    		return false;
    	if (!room.equals(other.getRoom()))
   			return false;
    	if (this.sumID()!=other.sumID())
    		return false;
    	if (this.multID()!=other.multID())
    		return false;
        return true;
    } 
    private int multID() {
        int mult = 1;
    	for (Tour t : tours)
    		mult *= t.getPrice();
    	return mult;
    } 
    private int sumID() {
        int sum = 0;
    	for (Tour t : tours)
    		sum += t.getPrice();
    	return sum;
    }
}