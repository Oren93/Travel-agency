// Forritið má þýða og keyra svona : Windows:
//    
//   java -cp .;sqlite-jdbc-3.32.3.2.jar HotelDataFactory
// á Unix:
//   javac HotelDataFactory.java
//   java -cp .;sqlite-jdbc-3.32.3.2.jar HotelDataFactory

package hotel;

import application.*;
import flights.*;


import java.io.*;
import java.sql.*;
import java.util.ArrayList;
//import java.util.Observable;
import java.util.Scanner;

//import HotelClasses.Booking;
//import HotelClasses.Guest;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelDataFactory
{

  public HotelDataFactory(){

  }

  public ArrayList<HotelRoom> findHotelRoom(Parameters p)
  throws ClassNotFoundException
  {
    ResultSet r = null;
    Statement statement = null;
    Connection connection = null;

    ArrayList<HotelRoom> hotelRoom = new ArrayList<>();
  

    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:Hotel.sqlite");
      statement = connection.createStatement();

      /**
       * SELECT hotelCity, hotelName, roomType, roomNumber, occupants, price, hotelStar, hotelAddress, disability FROM Hotel H, Rooms R WHERE
       * H.hotelName = R.hotel AND H.hotelCity = "Reykjavík" AND R.occupants >= 2 AND R.disability = "yes" AND price BETWEEN 5000 AND 5000000 AND NOT EXISTS(
       *    SELECT hotelBooking, roomNumberBooking, checkIn, checkOut FROM bookings BB WHERE
       *      (checkIn >= DATE('2022-03-07') AND checkIn < DATE('2022-03-10')) OR
       *      (checkOut > DATE('2022-03-07') AND checkOut < DATE('2022-03-10')));
       */


       int occupants = p.getgroupSize();
       int d = p.getDestination();
       String city = p.extractCode(d);
       int lP = p.getLowerPrice();
       int mP = p.getMaxPrice();
       String dis = "";
       LocalDate checkIn = p.getcheckIn();
       LocalDate checkOut = p.getcheckOut();


       if(p.getdifficulty() == 10)
       {
         dis = "yes";
       }
       else {
         dis = "no";
       }

      r = statement.executeQuery("SELECT hotelCity, hotelName, roomType, roomNumber, occupants, price, hotelStar, hotelAddress, disability FROM Hotel H, Rooms R WHERE " + 
					"H.hotelName = R.hotel AND hotelCity = '" + city + "' AND R.occupants >= " + occupants + " AND disability = '" + dis + "' AND price BETWEEN " + lP + " AND " + mP  + " AND NOT EXISTS(" + 
					"SELECT hotelBooking, roomNumberBooking, checkIn, checkOut FROM bookings BB WHERE" + 
                                    		"(checkIn >= DATE('" + checkIn + "') AND checkIn < DATE('" + checkOut + "')) OR" + 
                                    		"(checkOut > DATE('" + checkIn + "') AND checkOut < DATE('" + checkOut + "')))"
                                 );

      while(r.next())
      {
        

        String hotelCity = r.getString("hotelCity");
        String hotelName = r.getString("hotelName");
        String roomType = r.getString("roomType");
        String roomNumber = r.getString("roomNumber");
        int occupancy = r.getInt("occupants");
	int price = r.getInt("price");
        int hotelStar = r.getInt("hotelStar");
        String hotelAddress = r.getString("hotelAddress");
        String disability = r.getString("disability");

        // String city, String hotelName, String roomtype, String roomNumber, int occupancy, int pricePerNight, int hotelStar, String hotelAddress, String disability
        HotelRoom newHotelRoom = new HotelRoom(hotelCity, hotelName, roomType, roomNumber, occupants, price, hotelStar,hotelAddress,disability);
        
        hotelRoom.add(newHotelRoom);
        
      }
      return hotelRoom;
    }

    catch(SQLException e)
    {
      System.err.println(e.getMessage());

    }
    
    finally
    {

      try
      {
        if(connection != null) connection.close();
        if(r != null) r.close();
        if(statement != null) statement.close();

      }

      catch(SQLException e1)
      {
        // connection close failed.
        System.err.println(e1.getMessage() + "Villa í finally findHotel() finally");
      }
    }
    return hotelRoom;
  }
  
  public void newGuest(Passenger p)
  throws ClassNotFoundException
  {
    ResultSet r = null;
    Statement statement = null;
    Statement updateSM = null;
    Connection connection = null;

    // ArrayListt<Guest> addNewGuest = new ArrayList<>();

    // Finna hvort g.ssn er til í töflunni guest í gagnagrunni, Create ef ekki. Annars update.
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:Hotel.db");
      statement = connection.createStatement();
      updateSM = connection.createStatement();

      String fName = p.getFirstName();
      String lName = p.getLastName();
      String phoneNumber = "";
      String email = "";
      String ssn = p.getSsn();

      r = statement.executeQuery("SELECT ssn FROM Guest WHERE ssn = '" + ssn + "'");
      if (r.next() == false)
      {
        updateSM.executeUpdate("INSERT INTO Guest VALUES('" + fName + "', '" + lName + "', '" + phoneNumber + "', '" + email + "', '" + ssn + "')");
        //Guest addGuest = new Guest(fName, lName, phoneNumber, email, ssn);

        // addNewGuest.add(addGuest);
      }
      else
      {
        while(r.next())
        {
          updateSM.executeUpdate("UPDATE Guest SET fName = '" + fName + "', '" + lName + "', '" + phoneNumber + "', '" + email + "', '" + ssn + "' WHERE ssn = '" + ssn + "'");
          // Guest addGuest = new Guest(fName, lName, phoneNumber, email, ssn);
  
          // addNewGuest.add(addGuest);

        }
        // return addNewGuest;
      }
      
      
    }
    
    catch(SQLException e)
    {
      System.err.println(e.getMessage());

    }
    finally
    {

      try
      {
        if(connection != null) connection.close();
        if(r != null) r.close();
        if(statement != null) statement.close();

      }

      catch(SQLException e1)
      {
        // connection close failed.
        System.err.println(e1.getMessage() + "Villa í finally findHotel() finally");
      }
    }
  }

  public void booking(HotelRoom hr, Parameters p, Passenger pass)
  throws ClassNotFoundException
  {
    ResultSet r = null;
    Statement statement = null;
    Connection connection = null;

    // ArrayListt<Booking> addNewBooking = new ArrayList<>();

    // create booking; nota hr til að ná í infó fyrir það sem og frá parameters

    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:Hotel.db");
      statement = connection.createStatement();

      String hotelName = hr.getHotelName();
      String roomNumber = hr.getRoomNumber();
      LocalDate checkIn = p.getcheckIn();
      LocalDate checkOut = p.getcheckOut();
      String ssn = pass.getSsn();

      statement.executeUpdate("INSERT INTO bookings VALUES('" + hotelName + "', '" + roomNumber + "', '" + checkIn + "', '" + checkOut + "', '" + ssn + "')");
      

      //return addNewBooking;
    }
    
    catch(SQLException e)
    {
      System.err.println(e.getMessage());

    }
    
    finally
    {

      try
      {
        if(connection != null) connection.close();
        if(r != null) r.close();
        if(statement != null) statement.close();

      }

      catch(SQLException e1)
      {
        // connection close failed.
        System.err.println(e1.getMessage() + "Villa í finally findHotel() finally");
      }
    }
  }
}

