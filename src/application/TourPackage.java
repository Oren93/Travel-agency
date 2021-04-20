package application;
import daytour.*;
//import 3H.*;
import flights.*;

class TourTourPackage {
    //String TourPackageID;
    Tour [] tours;
    Flight [] flights;
    //Room [] rooms;
    double price;

    /**
	 * Constructor for an object type TourPackage
	 * @param pID TourPackage ID number
	 * @param t array of tours contained in the TourPackage 
	 * @param f array of flights contained in the TourPackage
	 * @param r array of rooms contained in the TourPackage
	 * @param p array of price contained in the TourPackage
	 */
    TourPackage (String pID, Tour [] t, Flight [] f/*,Room [] r*/, double p)
    {
        setTourPackageID(pID);
        setTours(t);
        setFlights(f);
        setRooms(r);
        setPrice(p);
    }

    /* set methods
    //private void setTourPackageID() {
    //    String packID = ; // add here math.Random to create TourPackage ID according to format
    //    TourPackageID = packID;
    }*/

    private void setTours(tr) {
        tours = tr;
    }

    private void setFlights(fli) {
        if(fli.length == 2) {
            flights = fli;
        }
        else printError("there should be only 2 flights");
    }

    private void setRooms(rs) {
        rooms = rs;
    }

    private void setPrice(pri) {
        price = pri;
    }

    // get methods
    String getTourPackageID() {
        return TourPackageID;
    }

    Tour [] getTours() {
        return tours;
    }

    Flight [] getFlights() {
        return flights;
    }

    Room [] getRooms() {
        return rooms,
    }

    float getPrice() {
        return price;
    }

    /**
	 * A temporary implementation of error handler, should later on pop an error message
	 * @param e the error string, indicates where the error occured
	 */
	private void printError (String e) {
		System.out.println("an error message popup should appear here");
		System.out.println("Error: "+ e);
		
	}
}