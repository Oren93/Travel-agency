package flights;

import java.time.LocalDateTime;
import java.util.ArrayList;

//flights class
public class Flight{
    private String flightNumber;
    private int total_seats;
    private int source;
    private int destination;
    private LocalDateTime dateDepartTime;
    private LocalDateTime dateArrivalTime;
    private ArrayList<Seat> seats;
    private int basePrice;


    public Flight(String flightNumber, int total_seats, int source, int destination, LocalDateTime dateDepartTime, LocalDateTime dateArrivalTime, ArrayList<Seat> seats, int basePrice) {
        this.flightNumber = flightNumber;
        this.total_seats = total_seats;
        this.source = source;
        this.destination = destination;
        this.dateDepartTime = dateDepartTime;
        this.dateArrivalTime = dateArrivalTime;
        this.seats = seats;
        this.basePrice = basePrice;
    }

    //check if the flight is full or not
    public boolean isFull(){
        if (totalAvailableSeats() <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    //gets number available seats for flight
    public int totalAvailableSeats(){
        int count = 0;

        for(Seat seat : seats)
            if(seat.isBooked() == false){
                count += 1;
            }

        return count;
    }

    //for showing the flights
    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", Time Departure='" + dateDepartTime + '\'' +
                ", Time Arrival='" + dateArrivalTime + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }

    public LocalDateTime getDateDepartTime(){
        return this.dateDepartTime;
    }

    public LocalDateTime getDateArrivalTime(){
        return this.dateArrivalTime;
    }

    public int getSource(){
        return this.source;
    }

    public int getDestination(){
        return this.destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public void setdateDepartTime(LocalDateTime dateDepartTime){
        this.dateDepartTime = dateDepartTime;
    }

    public void setdateArriveTime(LocalDateTime dateArrivalTime){
        this.dateArrivalTime = dateArrivalTime;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
}
