package hotel;


public class HotelRoom {
    private String city;
    private String hotelName;
    private String roomtype;
    private String roomNumber;
    private int occupancy;
    private int pricePerNight;
    private int hotelStar;
    private String hotelAddress;
    private String disability;

    public HotelRoom(String city, String hotelName, String roomtype, String roomNumber, int occupancy, int pricePerNight, int hotelStar, String hotelAddress, String disability) {
        this.city = city;
        this.hotelName = hotelName;
        this.roomtype = roomtype;
        this.roomNumber = roomNumber;
        this.occupancy = occupancy;
        this.pricePerNight = pricePerNight;
        this.hotelStar = hotelStar;
        this.hotelAddress = hotelAddress;
        this.disability = disability;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }
    
    public String getRoomNumber() {
      return roomNumber;
  }

  public void setRoomNumber(String roomNumber) {
      this.roomNumber = roomNumber;
  }

    public int getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
    	this.pricePerNight = pricePerNight;
    }

    public int getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(int hotelStar) {
        this.hotelStar = hotelStar;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }
}
