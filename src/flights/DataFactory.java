package flights;

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

        // 23. april - 30. april short
        LocalDateTime departure1 = LocalDateTime.of(2021,04, 23, 14,30);
        LocalDateTime arrival1 = LocalDateTime.of(2021,04, 23, 15,10);
        LocalDateTime departure_back1 = LocalDateTime.of(2021,04, 30, 17,30);
        LocalDateTime arrival_back1 = LocalDateTime.of(2021,04, 30, 18,10);

        // 24. april - 29. april short
        LocalDateTime departure2 = LocalDateTime.of(2021,04, 24, 14,30);
        LocalDateTime arrival2 = LocalDateTime.of(2021,04, 24, 15,10);
        LocalDateTime departure_back2 = LocalDateTime.of(2021,04, 29, 17,30);
        LocalDateTime arrival_back2 = LocalDateTime.of(2021,04, 29, 18,10);

        // 23. april - 29. april long
        LocalDateTime departure3 = LocalDateTime.of(2021,04, 23, 10,00);
        LocalDateTime arrival3 = LocalDateTime.of(2021,04, 23, 11,10);
        LocalDateTime departure_back3 = LocalDateTime.of(2021,04, 30, 19,20);
        LocalDateTime arrival_back3 = LocalDateTime.of(2021,04, 30, 20,20);

        // 24. april - 29. april long
        LocalDateTime departure4 = LocalDateTime.of(2021,04, 24, 9,30);
        LocalDateTime arrival4 = LocalDateTime.of(2021,04, 24, 10,40);
        LocalDateTime departure_back4 = LocalDateTime.of(2021,04, 29, 20,10);
        LocalDateTime arrival_back4 = LocalDateTime.of(2021,04, 29, 21,20);

        // 23. april - 30. april short
        LocalDateTime departure5 = LocalDateTime.of(2021,04, 23, 8,30);
        LocalDateTime arrival5 = LocalDateTime.of(2021,04, 23, 9,10);
        LocalDateTime departure_back5 = LocalDateTime.of(2021,04, 30, 10,30);
        LocalDateTime arrival_back5 = LocalDateTime.of(2021,04, 30, 11,10);

        // 24. april - 29. april short
        LocalDateTime departure6 = LocalDateTime.of(2021,04, 24, 15,30);
        LocalDateTime arrival6 = LocalDateTime.of(2021,04, 24, 16,10);
        LocalDateTime departure_back6 = LocalDateTime.of(2021,04, 29, 19,30);
        LocalDateTime arrival_back6 = LocalDateTime.of(2021,04, 29, 20,10);

        // 23. april - 30. april long
        LocalDateTime departure7 = LocalDateTime.of(2021,04, 23, 7,00);
        LocalDateTime arrival7 = LocalDateTime.of(2021,04, 23, 8,10);
        LocalDateTime departure_back7 = LocalDateTime.of(2021,04, 30, 12,20);
        LocalDateTime arrival_back7 = LocalDateTime.of(2021,04, 30, 13,20);

        // 24. april - 29. april long
        LocalDateTime departure8 = LocalDateTime.of(2021,04, 24, 10,30);
        LocalDateTime arrival8 = LocalDateTime.of(2021,04, 24, 11,40);
        LocalDateTime departure_back8 = LocalDateTime.of(2021,04, 29, 13,10);
        LocalDateTime arrival_back8 = LocalDateTime.of(2021,04, 29, 14,20);

        //creates mock flights
        Flight flight1a = new Flight(
                "FI741",
                myNum[(int) Math.random() * 4],
                CNST.AK,
                CNST.RVK,
                departure1,
                arrival1,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight1a.setSeats(getSeats(flight1a.getFlightNumber(), flight1a.getTotal_seats()));
        flightslist.add(flight1a);

        Flight flight1b = new Flight(
                "FI742",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.AK,
                departure_back1,
                arrival_back1,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight1b.setSeats(getSeats(flight1b.getFlightNumber(), flight1b.getTotal_seats()));
        flightslist.add(flight1b);

        Flight flight2a = new Flight(
                "FI743",
                myNum[(int) Math.random() * 4],
                CNST.AK,
                CNST.RVK,
                departure2,
                arrival2,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight2a.setSeats(getSeats(flight2a.getFlightNumber(), flight2a.getTotal_seats()));
        flightslist.add(flight2a);

        Flight flight2b = new Flight(
                "FI744",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.AK,
                departure_back2,
                arrival_back2,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight2b.setSeats(getSeats(flight2b.getFlightNumber(), flight2b.getTotal_seats()));
        flightslist.add(flight2b);

        Flight flight3a = new Flight(
                "FI745",
                myNum[(int) Math.random() * 4],
                CNST.AK,
                CNST.RVK,
                departure3,
                arrival3,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight3a.setSeats(getSeats(flight3a.getFlightNumber(), flight3a.getTotal_seats()));
        flightslist.add(flight3a);

        Flight flight3b = new Flight(
                "FI746",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.AK,
                departure_back3,
                arrival_back3,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight3b.setSeats(getSeats(flight3b.getFlightNumber(), flight3b.getTotal_seats()));
        flightslist.add(flight3b);

        Flight flight4a = new Flight(
                "FI747",
                myNum[(int) Math.random() * 4],
                CNST.AK,
                CNST.RVK,
                departure4,
                arrival4,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight4a.setSeats(getSeats(flight4a.getFlightNumber(), flight4a.getTotal_seats()));
        flightslist.add(flight4a);

        Flight flight4b = new Flight(
                "FI748",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.AK,
                departure_back4,
                arrival_back4,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight4b.setSeats(getSeats(flight4b.getFlightNumber(), flight4b.getTotal_seats()));
        flightslist.add(flight4b);

        Flight flight5a = new Flight(
                "FI747",
                myNum[(int) Math.random() * 4],
                CNST.AK,
                CNST.RVK,
                departure5,
                arrival5,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight5a.setSeats(getSeats(flight5a.getFlightNumber(), flight5a.getTotal_seats()));
        flightslist.add(flight5a);

        Flight flight5b = new Flight(
                "FI748",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.AK,
                departure_back5,
                arrival_back5,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight5b.setSeats(getSeats(flight5b.getFlightNumber(), flight5b.getTotal_seats()));
        flightslist.add(flight5b);

        Flight flight6a = new Flight(
                "FI749",
                myNum[(int) Math.random() * 4],
                CNST.AK,
                CNST.RVK,
                departure6,
                arrival6,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight6a.setSeats(getSeats(flight6a.getFlightNumber(), flight6a.getTotal_seats()));
        flightslist.add(flight6a);

        Flight flight6b = new Flight(
                "FI750",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.AK,
                departure_back6,
                arrival_back6,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight6b.setSeats(getSeats(flight6b.getFlightNumber(), flight6b.getTotal_seats()));
        flightslist.add(flight6b);

        Flight flight7a = new Flight(
                "FI751",
                myNum[(int) Math.random() * 4],
                CNST.AK,
                CNST.RVK,
                departure7,
                arrival7,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight7a.setSeats(getSeats(flight7a.getFlightNumber(), flight7a.getTotal_seats()));
        flightslist.add(flight7a);

        Flight flight7b = new Flight(
                "FI751",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.AK,
                departure_back7,
                arrival_back7,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight7b.setSeats(getSeats(flight7b.getFlightNumber(), flight7b.getTotal_seats()));
        flightslist.add(flight7b);

        Flight flight8a = new Flight(
                "FI753",
                myNum[(int) Math.random() * 4],
                CNST.AK,
                CNST.RVK,
                departure8,
                arrival8,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight8a.setSeats(getSeats(flight8a.getFlightNumber(), flight8a.getTotal_seats()));
        flightslist.add(flight8a);

        Flight flight8b = new Flight(
                "FI754",
                myNum[(int) Math.random() * 4],
                CNST.RVK,
                CNST.AK,
                departure_back8,
                arrival_back8,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight8b.setSeats(getSeats(flight8b.getFlightNumber(), flight8b.getTotal_seats()));
        flightslist.add(flight8b);

        // Flight flight2 = new Flight(
        //         "FI852",
        //         myNum[(int) Math.random() * 4],
        //         CNST.RVK,
        //         CNST.HUS,
        //         departure,
        //         arrival,
        //         null,
        //         30000 + (int) Math.random() * (100000 - 30000 + 1)
        // );
        // flight2.setSeats(getSeats(flight2.getFlightNumber(), flight2.getTotal_seats()));
        // flightslist.add(flight2);

        // Flight flight3 = new Flight(
        //         "FI574",
        //         myNum[(int) Math.random() * 4],
        //         CNST.RVK,
        //         CNST.ISAF,
        //         departure,
        //         arrival,
        //         null,
        //         30000 + (int) Math.random() * (100000 - 30000 + 1)
        // );
        // flight3.setSeats(getSeats(flight3.getFlightNumber(), flight3.getTotal_seats()));
        // flightslist.add(flight3);

        // Flight flight4 = new Flight(
        //         "FEI943",
        //         myNum[(int) Math.random() * 4],
        //         CNST.RVK,
        //         CNST.VEST,
        //         departure,
        //         arrival,
        //         null,
        //         30000 + (int) Math.random() * (100000 - 30000 + 1)
        // );
        // flight4.setSeats(getSeats(flight4.getFlightNumber(), flight4.getTotal_seats()));
        // flightslist.add(flight4);

        // Flight flight5 = new Flight(
        //         "FEI638",
        //         myNum[(int) Math.random() * 4],
        //         CNST.HUS,
        //         CNST.RVK,
        //         departure,
        //         arrival,
        //         null,
        //         30000 + (int) Math.random() * (100000 - 30000 + 1)
        // );
        // flight5.setSeats(getSeats(flight5.getFlightNumber(), flight5.getTotal_seats()));
        // flightslist.add(flight5);

        // Flight flight6 = new Flight(
        //         "FEI422",
        //         myNum[(int) Math.random() * 4],
        //         CNST.EGIL,
        //         CNST.RVK,
        //         departure,
        //         arrival,
        //         null,
        //         30000 + (int) Math.random() * (100000 - 30000 + 1)
        // );
        // flight6.setSeats(getSeats(flight6.getFlightNumber(), flight6.getTotal_seats()));
        // flightslist.add(flight6);

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
