package flights;

public class Seat {
    private String seatNumber;
    private String flightNumber;
    private boolean isBooked;
    private String seatClass;

    public Seat(String seatNumber, String flightNumber, boolean isBooked, String seatClass) {
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
        this.isBooked = isBooked;
        this.seatClass = seatClass;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

}
