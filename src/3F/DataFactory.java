package sample;

import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataFactory {

    public static ArrayList<Seat> getSeats(String flightNumber, int total_seats) {
        ArrayList<Seat> seats = new ArrayList<Seat>();
        for (int k = 1; k <= (total_seats / 4); k++) {
            String flightClass;
            if (k < (total_seats / 4)) {
                flightClass = "firstClass";
            } else {
                flightClass = "economyClass";
            }

            seats.add(new Seat(Integer.toString(k) + "A", flightNumber, false,flightClass));
            seats.add(new Seat(Integer.toString(k) + "B",flightNumber, false,flightClass));
            seats.add(new Seat(Integer.toString(k) + "C",flightNumber, false,flightClass));
            seats.add(new Seat(Integer.toString(k) + "D", flightNumber, false,flightClass));
        }

        seats.get(0).setBooked(true);
        seats.get(1).setBooked(true);
        seats.get(2).setBooked(true);

        return seats;
    }

    public static ObservableList<Flight> getFlights() {
        ObservableList<Flight> flightslist = FXCollections.observableArrayList();

        int[] myNum = {20, 32, 40, 72};

        LocalDateTime departure = LocalDateTime.of(2021,07, 14, 14,30);
        LocalDateTime arrival = LocalDateTime.of(2021,07, 14, 15,10);
        LocalDateTime departure_back = LocalDateTime.of(2021,07, 21, 17,30);
        LocalDateTime arrival_back = LocalDateTime.of(2021,07, 21, 18,10);

        //creates mock flights
        Flight flight1 = new Flight(
                "FI741",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.AK,
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight1.setSeats(getSeats(flight1.getFlightNumber(), flight1.getTotal_seats()));
        flightslist.add(flight1);

        Flight flight2 = new Flight(
                "FI852",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.HUS,
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight2.setSeats(getSeats(flight2.getFlightNumber(), flight2.getTotal_seats()));
        flightslist.add(flight2);

        Flight flight3 = new Flight(
                "FI574",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.ISAF,
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight3.setSeats(getSeats(flight3.getFlightNumber(), flight3.getTotal_seats()));
        flightslist.add(flight3);

        Flight flight4 = new Flight(
                "FEI943",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.VEST,
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight4.setSeats(getSeats(flight4.getFlightNumber(), flight4.getTotal_seats()));
        flightslist.add(flight4);

        Flight flight5 = new Flight(
                "FEI638",
                myNum[(int) Math.random() * 4],
                CNST.HUS,
                CNST.RVK,
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight5.setSeats(getSeats(flight5.getFlightNumber(), flight5.getTotal_seats()));
        flightslist.add(flight5);

        Flight flight6 = new Flight(
                "FEI422",
                myNum[(int) Math.random() * 4],
                CNST.EGIL,
                CNST.RVK,
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight6.setSeats(getSeats(flight6.getFlightNumber(), flight6.getTotal_seats()));
        flightslist.add(flight6);

        Flight flight7 = new Flight(
                "FI742",
                myNum[(int) Math.random() * 4],
                CNST.AK,
                CNST.RVK,
                departure_back,
                arrival_back,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight7.setSeats(getSeats(flight7.getFlightNumber(), flight7.getTotal_seats()));
        flightslist.add(flight7);
        return flightslist;
    }

    //creates mock passengers and bookings for them
    public static ObservableList<Passenger> getPassengers(){

        ObservableList<Passenger> passengers = FXCollections.observableArrayList();
        Passenger passenger1 = new Passenger("1", "Sigga", "Hall");
        Passenger passenger2 = new Passenger("2", "Signý", "Hall");
        Passenger passenger3 = new Passenger("3", "Inga", "Hall");

        ObservableList<Flight> flights = getFlights();
        Flight flight = flights.get(0);

        ArrayList seatsToBook = new ArrayList<>();
        seatsToBook.add(flight.getSeats().get(0));
        seatsToBook.add(flight.getSeats().get(1));
        seatsToBook.add(flight.getSeats().get(2));

        ArrayList<Booking> bookings1 = new ArrayList<>();
        bookings1.add(new Booking(seatsToBook, passenger1, 1, 1,1,1, false));
        passenger1.setBookings(bookings1);
        System.out.println(bookings1.size());


        Flight flight1 = flights.get(1);
        ArrayList seatsToBook1 = new ArrayList<>();
        seatsToBook1.add(flight1.getSeats().get(0));
        seatsToBook1.add(flight1.getSeats().get(1));

        ArrayList<Booking> bookings2 = new ArrayList<>();
        bookings2.add(new Booking(seatsToBook1, passenger2, 1, 1,1,1, false));
        passenger2.setBookings(bookings2);

        Flight flight2 = flights.get(2);
        ArrayList seatsToBook2 = new ArrayList<>();
        seatsToBook2.add(flight2.getSeats().get(3));
        seatsToBook2.add(flight2.getSeats().get(4));
        ArrayList<Booking> bookings3 = new ArrayList<>();
        bookings3.add(new Booking(seatsToBook2, passenger3, 1, 1,1,1, false));
        passenger3.setBookings(bookings3);

        passengers.add(passenger1);
        passengers.add(passenger2);
        passengers.add(passenger3);

        return passengers;
    }

}
