// package application;


public class HotelRoom {
    private String City;
    private String HotelName;
    private String Roomtype;
    private String RoomNumber;
    private int Occupancy;
    private int PricePerNight;
    private int HotelStar;
    private String HotelAddress;
    private String Disability;

    public HotelRoom(String city, String hotelName, String roomtype, String roomNumber, int occupancy, int pricePerNight, int hotelStar, String hotelAddress, String disability) {
        this.City = city;
        this.HotelName = hotelName;
        this.Roomtype = roomtype;
        this.RoomNumber = roomNumber;
        this.Occupancy = occupancy;
        this.PricePerNight = pricePerNight;
        this.HotelStar = hotelStar;
        this.HotelAddress = hotelAddress;
        this.Disability = disability;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getRoomtype() {
        return Roomtype;
    }

    public void setRoomtype(String roomtype) {
        Roomtype = roomtype;
    }
    
    public String getRoomNumber() {
      return RoomNumber;
  }

  public void setRoomNumber(String roomNumber) {
      RoomNumber = roomNumber;
  }

    public int getOccupancy() {
        return Occupancy;
    }

    public void setOccupancy(int occupancy) {
        Occupancy = occupancy;
    }

    public int getPricePerNight() {
        return PricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        PricePerNight = pricePerNight;
    }

    public int getHotelStar() {
        return HotelStar;
    }

    public void setHotelStar(int hotelStar) {
        HotelStar = hotelStar;
    }

    public String getHotelAddress() {
        return HotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        HotelAddress = hotelAddress;
    }

    public String getDisability() {
        return Disability;
    }

    public void setDisability(String disability) {
        Disability = disability;
    }
}
