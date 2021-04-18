package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FlightController implements Initializable{

   private DataFactory dataFactory = new DataFactory();
   private ObservableList<Flight> flights = FXCollections.observableArrayList();
   private ObservableList<Passenger> passengers = FXCollections.observableArrayList();

   @Override
   public void initialize(URL location, ResourceBundle resources) {
       passengers = dataFactory.getPassengers();
       flights = dataFactory.getFlights();
   }


    //takes in the parameters and boolean isTo, 
    //isTo is true if searching for the flights from source to destination
    //isTo is false if searching for flights from destination to source
   public static ObservableList<Flight> getAvailableFlights(Parameters p, boolean isTo){
       ObservableList<Flight> availableFlights = FXCollections.observableArrayList();

       int groupSize = p.getgroupSize();
       int source = 0;
       int destination = 0;
       LocalDate date = null;
       if(isTo){
           date = p.getcheckIn();

           source = p.getdepartLocation();
           destination = p.getDestination();
       }
       else{
           date = p.getcheckOut();

           source = p.getDestination();
           destination = p.getdepartLocation();
       }

       for(Flight flight : DataFactory.getFlights()){
           if (date.equals(flight.getDateArrivalTime().toLocalDate()) &&
                   source == flight.getSource() &&
                   destination == flight.getDestination() &&
                   flight.totalAvailableSeats() >= groupSize) {
                   availableFlights.add(flight);
           }
       }

       return availableFlights;
    }

    //Takes only in flightNumber and returns an ObserableList of seat objects 
    //that are available for that flight
    public static ObservableList<Seat> getAvailableSeats(String flightNumber){
        ObservableList<Seat> availableSeats = FXCollections.observableArrayList();

        Flight ourFlight = null;
        for(Flight flight : DataFactory.getFlights()){
            if(flight.getFlightNumber().equals(flightNumber)){
                ourFlight = flight;
                break;
            }
        }

        ArrayList<Seat> ourSeats = ourFlight.getSeats();

        for(Seat seat : ourSeats){
            if(!seat.isBooked()){
                availableSeats.add(seat);
            }
        }

        return availableSeats;
    }

    //method that makes new booking and makes isBooked true for corrisponding seats
    public static boolean bookFlight(String flightNumber, ArrayList<Seat> seats, Passenger passenger, int bags, int oddSized, int pillows, int blankets, boolean disability) {
        boolean b = false;

        ArrayList<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking(seats, passenger, bags, oddSized, pillows, blankets, disability);
        bookings.add(booking);
        passenger.setBookings(bookings);

        Flight ourFlight = null;
        for (Flight flight : DataFactory.getFlights()) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                ourFlight = flight;
                break;
            }
        }

        ArrayList<Seat> flightSeats = ourFlight.getSeats();

        int i = 0;
        for (Seat seat : flightSeats) {
            String seatNumber = seat.getSeatNumber();
            if (i >= seats.size()) {
                break;
            }
            String seatNumber2 = seats.get(i).getSeatNumber();
            if (seatNumber.equals(seatNumber2)) {
                seat.setBooked(true);
                i += 1;
            }
        }
        if(bookings.size() != 0){
            b = true;
        }
        return b;

    }

}    
