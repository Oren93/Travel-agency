package sample;

import javafx.collections.ObservableList;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GetAvaliableFlightsTest {
    private Controller controller;

    private DataFactory dataFactory = new DataFactory();

    @Test
    public void testAvailableFlightsFromReykjavikToAkureyri() {
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,07, 14), CNST.RVK, CNST.AK,5);

        for (Flight flight : flights) {
            assertEquals(2021, flight.getDateArrivalTime().getYear());
            assertEquals(07,  flight.getDateArrivalTime().getMonthValue());
            assertEquals(14,  flight.getDateArrivalTime().getDayOfMonth());
            assertEquals(CNST.RVK, flight.getSource());
            assertEquals(CNST.AK, flight.getDestination());
        }

    }

    @Test
    public void testAvailableSeats(){
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,07, 14), CNST.RVK, CNST.AK,5);
        ObservableList<Seat> seats = controller.getAvailableSeats(flights.get(0).getFlightNumber());
        Assert.assertEquals(17, seats.size());

    }

    @Test
    public void testBookFlight(){
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,07, 14), CNST.RVK, CNST.HUS,5);
        Flight flight = flights.get(0);
        Passenger passenger = new Passenger("123", "Sigga", "Þóra");
        ArrayList<Seat> seats = new ArrayList<>();
        seats.add(flight.getSeats().get(0));
        seats.add(flight.getSeats().get(1));
        Boolean b =  controller.bookFlight(flight.getFlightNumber(), seats, passenger,1, 1, 1,1,false);
        assertTrue(b);

    }

}
