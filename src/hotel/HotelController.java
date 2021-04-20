package hotel;

import java.util.ResourceBundle;

import java.net.URL;

//import javax.print.DocFlavor.URL;

import application.*;
import flights.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class HotelController implements Initializable {

    private static HotelDataFactory hoteRoomDB = new HotelDataFactory();

    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public static ObservableList<HotelRoom> GetHotelRooms(Parameters p){
            ObservableList<HotelRoom> hotelrooms = FXCollections.observableArrayList();
        
            // finds and returns list of available rooms given the criteria is met.
            try {
            hotelrooms = FXCollections.observableArrayList(hoteRoomDB.findHotelRoom(p));
            //hotelrooms.add(hoteRoomDB.findHotelRoom(p));
            } catch (ClassNotFoundException e) {
            }
            
            return hotelrooms;
     }
     public static boolean bookRoom(String hotelName, Parameters p, Passenger pass){
           // Finds whether an ssn exists in the db table Guest.
           // If it exists it'll enter user informatior to the table, else it will just update the existing user in case they have changed their names etc.
    	   try {
			hoteRoomDB.newGuest(pass);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

            // Here we call the GetHotelRooms method with the Parameters p as parameter.
            ObservableList<HotelRoom> hotelrooms = GetHotelRooms(p);
            boolean ok = false;
            int occupantsLeft = p.getgroupSize();

            // If the room search criteria is met and finds available rooms then here we will loop through each hotelRoom and 
            // book the propper room if it mathces the name of hotel in our hotel table database
            for(HotelRoom hr : hotelrooms){
                if(occupantsLeft <= 0) break;
                if (hr.getHotelName() == hotelName) {
                    // Here we create the booking from the Parameters class.
                	try {
						hoteRoomDB.booking(hr, p, pass);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    occupantsLeft -= hr.getOccupancy();
                }
                // If occupantsLeft is at zero or less then all guests have gotten a room and will return true.
                // Otherwise it will return false.
                if (occupantsLeft <= 0) ok = true;
                return ok;
            }
            return false;
        }
    }
